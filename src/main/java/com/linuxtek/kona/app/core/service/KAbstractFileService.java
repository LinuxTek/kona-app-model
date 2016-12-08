/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KFile;
import com.linuxtek.kona.app.core.entity.KFileAccess;
import com.linuxtek.kona.app.core.entity.KFileType;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.app.core.entity.KUserRole;
import com.linuxtek.kona.app.core.entity.KUserType;
import com.linuxtek.kona.app.util.KUtil;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;
import com.linuxtek.kona.encryption.KEncryptUtil;
import com.linuxtek.kona.http.KMimeTypes;
import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KFileUtil;


public abstract class KAbstractFileService<F extends KFile,EXAMPLE,U extends KUser> 
		extends KAbstractService<F,EXAMPLE>
		implements KFileService<F> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractFileService.class);

	// ----------------------------------------------------------------------------

	protected abstract F getNewObject();

	protected abstract <S extends KUserService<U>> S getUserService();

	// ----------------------------------------------------------------------------

	protected abstract String getPublicBaseUrl();

	protected abstract String getLocalBasePath();

	protected String generateUid() {
		return KUtil.uuid();
	}
	
	protected String toPublicPath(String localPath) {
		return localPath;
	}

	// ----------------------------------------------------------------------------

	@Override 
	public void validate(F file) {
		if (file.getCreatedDate() == null) {
			file.setCreatedDate(new Date());
		}

		if (file.getUid() == null) {
			file.setUid(generateUid());
		}

		file.setLastUpdated(new Date());
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<F> fetchByCriteria(Integer startRow, Integer resultSize,
			String[] sortOrder, Map<String, Object> filter, boolean distinct, 
			boolean withData) throws IOException {

		List<F> list = fetchByCriteria(startRow, resultSize, sortOrder, filter, distinct); 

		if (!withData) {
			return list;
		}

		for (F file : list) {
			fetchFile(file);
		}

		return list;
	}

	// ----------------------------------------------------------------------------

	@Override
	public F fetchByUid(String uid, boolean withData) throws IOException {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false, withData));
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public List<F> fetchByParentId(Long parentId, boolean withData) throws IOException {
		Map<String,Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
		return fetchByCriteria(0, 99999, null, filter, false, withData);
	}

	// ----------------------------------------------------------------------------

	@Override
	public List<F> fetchByUserId(Long userId, boolean withData) throws IOException {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return fetchByCriteria(0, 99999, null, filter, false, withData);
	}

	// ----------------------------------------------------------------------------

	@Override
	public F add(F file) {
		validate(file);

		if (file.getData() == null) {
			throw new IllegalArgumentException("data is null");
		}

		Integer size = file.getData().length;
		
		file.setSize(size.longValue());

		String localPath = createFilePath(file);
		
		file.setLocalPath(localPath);

		if (file.getUrlPath() == null) {
			file.setUrlPath(toPublicPath(localPath));
		}


		logger.debug("server add(File) called:"
				+ "\n\tdata size: " + size
				+ "\n\tfile: " + file);

		try {
			saveFile(file);
			getDao().insert(file);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return file;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public void remove(F file) {
		try {
			if (file == null) return;
			deleteFile(file);
			getDao().deleteByPrimaryKey(file.getId());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// ----------------------------------------------------------------------------

	@Override 
	public void removeById(Long fileId) {
		F file = fetchById(fileId);
		remove(file);
	}
	
	// ----------------------------------------------------------------------------

	@Override 
	public F update(F file) {
		validate(file);

		boolean updateData = false;
		
		if (file.getData() != null) {
			Integer size = file.getData().length;
			file.setSize(size.longValue());
			updateData = true;
		}

		// just for debugging purposes
		F old = fetchById(file.getId());
		logger.debug("server update:\n\t" + KClassUtil.toString(old));


		try {
			if (updateData)  {
				saveFile(file);
			}

			getDao().updateByPrimaryKey(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return file;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public F fetchById(Long fileId) {
		try {
			return fetchById(fileId, false);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public F fetchById(Long fileId, boolean withData) throws IOException {
		F file = getDao().selectByPrimaryKey(fileId);

		if (file == null) return null;

		if (withData) {
			file = fetchFile(file);
		}

		return file;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public String toAbsoluteUrl(String publicPath) {
		if (publicPath == null) return null;

		String publicUrl = null;
		String publicBaseUrl = getPublicBaseUrl();
		if (publicBaseUrl != null) {
			if (publicPath.startsWith("/")) {
				publicPath = publicPath.substring(1, publicPath.length());
			}
			if (!publicBaseUrl.endsWith("/")) {
				publicBaseUrl += "/";
			}
			publicUrl = publicBaseUrl + publicPath;
		}
		return publicUrl;
	}   
    
	
	// ----------------------------------------------------------------------------
    
    // Removing this 
    /*
	@Override
	public String toServerLocalPath(String publicPath) {
		String localBasePath = getLocalBasePath();
		
		if (!localBasePath.endsWith(("/"))) {
			localBasePath += "/";
		}
        
		Map<String,Object> filter = KMyBatisUtil.createFilter("urlPath", publicPath);
        
		F file = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));

		if (file == null) {
			throw new IllegalArgumentException("File not found for path: " + publicPath);
		}
		
		String fullPath = localBasePath + file.getLocalPath();
        
        return fullPath;
	}
    */
	
	// ----------------------------------------------------------------------------

	@Override
	public F fetchFileByUrlPath(String publicPath) throws IOException {

		Map<String,Object> filter = KMyBatisUtil.createFilter("urlPath", publicPath);
		F file = KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false, false));

		if (file == null) {
			throw new IOException("File not found for path: " + publicPath);
		}

		String localBasePath = getLocalBasePath();
		
		if (!localBasePath.endsWith(("/"))) {
			localBasePath += "/";
		}
		
		String fullPath = localBasePath + file.getLocalPath();

		logger.debug("fullPath: " + fullPath);

		byte[] fileData = KFileUtil.toByteArray(fullPath);
	

		String contentType = file.getContentType();
		
		if (contentType == null) {
			try {
				contentType = KMimeTypes.getContentType(fileData);
			} catch (Exception e) {
				logger.debug("Unable to determine contentType for fileId: " + file.getId(), e);
			}
		}

		if (contentType == null) {
			throw new IOException("Cannot determine file content type: " + fullPath);
		}

		Path path = Paths.get(fullPath);
		String filename = path.getFileName().toString();

		if (file.getName() == null && filename != null) {
			file.setName(filename);
		}

		logger.debug("fetchFileByUrlPath: filename: " + file.getName());

		file.setData(fileData);
		file.setContentType(contentType);

		return file;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public F fetchFile(F file) throws IOException {
		String urlPath = file.getUrlPath();
		
		if (urlPath == null) {
			file = fetchById(file.getId(), false);
			urlPath = file.getUrlPath();
		}

		F f = fetchFileByUrlPath(urlPath);

		file.setData(f.getData());

		if (file.getSize() == null) {
			file.setSize(f.getSize());
		}

		if (file.getName() == null) {
			file.setName(f.getName());
		}

		if (file.getContentType() == null) {
			file.setContentType(f.getContentType());
		}
		
		return file;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void saveFile(F file) throws IOException {
		String localBasePath = getLocalBasePath();
		
		if (!localBasePath.endsWith(("/"))) {
			localBasePath += "/";
		}
		
		String fullPath = localBasePath + file.getLocalPath();
		
		deleteFile(file);
		
		KFileUtil.writeFile(fullPath, file.getData());

		if (file.getContentType() == null || file.getContentType().equalsIgnoreCase("application/octet-stream")) { 
			logger.debug("Probing contentType for file: " + fullPath);
			String contentType = KMimeTypes.getContentType(file.getData());
			logger.debug("Probed contentType: " + contentType);

			if (contentType != null) {
				file.setContentType(contentType);

				if (file.getTypeId() == null ) {
					file.setTypeId(KFileType.getInstance(contentType).getId());
				}
			}
		}
	}
	
	// ----------------------------------------------------------------------------
	
	@Override
	public void deleteFile(F file) throws IOException {
		String localBasePath = getLocalBasePath();
		
		if (!localBasePath.endsWith(("/"))) {
			localBasePath += "/";
		}
		
		String fullPath = localBasePath + file.getLocalPath();
		
		java.io.File f = new java.io.File(fullPath);
		
		if (f.exists()) {
			f.delete();
		} else {
			logger.warn("File does not exist: " + fullPath);
		}
	}
	
	// ----------------------------------------------------------------------------

	private String createFilePath(F file) {

		String fileKey = UUID.randomUUID().toString();

		try {
			fileKey = KEncryptUtil.MD5(fileKey).toUpperCase();
		} catch (Exception e) { 
			throw new RuntimeException(e);
		}

		int hashcode = fileKey.hashCode();
		int mask = 255;

		int dir1 = hashcode & mask;
		int dir2 = (hashcode >> 8) & mask;
		int dir3 = ((hashcode >> 8) >> 8) & mask;
		int dir4 = (((hashcode >> 8) >> 8)>>8) & mask;

		StringBuilder path = new StringBuilder();
		path.append(String.format("%04d", dir1));
		path.append(java.io.File.separator);
		path.append(String.format("%04d", dir2));
		path.append(java.io.File.separator);
		path.append(String.format("%04d", dir3));
		path.append(java.io.File.separator);
		path.append(String.format("%04d", dir4));
		path.append(fileKey);

		logger.debug("Created path string: " + path);

		return path.toString();
	}

	// ----------------------------------------------------------------------------
	
	@Override
	public boolean isAuthorized(F file, String clientId, String accessToken) {
		logger.debug("FileService: isAuthorized: checking file: " + KClassUtil.toString(file));

		KFileAccess access = KFileAccess.getInstance(file.getAccessId());
		if (access == null) {
			throw new IllegalStateException("Invalid file access level: " + file.getAccessId());
		}

		if (access == KFileAccess.PUBLIC) return true;
		if (access == KFileAccess.NONE) return false;

		// FIXME: any valid app can access
		if (access == KFileAccess.APP) {
			// access level is app and we have a valid user
			return true;
		}


		// SYSTEM, OWNER, CONTACT and FRIENDS can access
		if (access == KFileAccess.FRIEND) {
			return true;
		}

		U user = getUserService().fetchByAccessToken(accessToken, false);
		
		if (user == null || !user.isActive() || !user.isEnabled()) return false;

		// FIXME: only SYSTEM users can access
		if (access == KFileAccess.SYSTEM) {
			KUserType userType = KUserType.getInstance(user.getTypeId());
			if (userType == KUserType.SYSTEM) return true;
			if (KUserRole.haveRole(user.getRoles(), KUserRole.ADMIN)) return true;
			if (KUserRole.haveRole(user.getRoles(), KUserRole.SYSTEM)) return true;
			return false;
		}


		// FIXME: SYSTEM, OWNER can access
		if (access == KFileAccess.OWNER){
			if (file.getUserId() == null) {
				logger.warn("File has USER access but userId is null");
				return false;
			}

			if (file.getUserId().equals(user.getId())) return true;
			return false;
		}

		return false;
	}
}
