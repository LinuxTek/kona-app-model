package com.linuxtek.kona.app.core.entity;

import java.util.Date;

import com.linuxtek.kona.data.entity.KEntityObject;

public interface KFile extends KEntityObject {
    public Long getId();
    public void setId(Long id);
    
    public String getUid();
    public void setUid(String uid);

    public Long getTypeId();
    public void setTypeId(Long typeId);

    public Long getAccessId();
    public void setAccessId(Long accessId); // for ex. user, app, public

    public Long getParentId(); //for multi-part docs such as email w/attachments
    public void setParentId(Long parentId);

    public Long getUserId();
    public void setUserId(Long userId);

	public String getName();
	public void setName(String name);

	public String getContentType();
	public void setContentType(String contentType);

	public Long getSize();
	public void setSize(Long bytes);

	public byte[] getData();
	public void setData(byte[] data);


    /**
     * Flag to indicate if this file should be hidden from the user's
     * normal view.  The flag is controlled by the user.
     */
    public boolean isHidden();
    public void setHidden(boolean hidden);

    /**
     * Flag to indicate if this file is currently enabled.
     * This flag is set by the system to control the user's  access
     * to the file.  For example, this flag may be set to false
     * if the file is pending some type of review.
     */
    public boolean isEnabled();
    public void setEnabled(boolean enabled);

    /**
     * Flag to indicate if this file is currently active.
     * An active file is one which is not retired (deleted).
     * If set to true, getRetiredDate() should be set to null, otherwise
     * it should return the date the file was retired.
     */
    public boolean isActive();
    public void setActive(boolean active);
    
    
    /**
     * Flag to indicate of this file is a temp file that can be 
     * automatically disposed when the system is shutdown.
     * 
     * Use case: create a public url for an image that needs to be uploaded 
     * by an external service.  Once the service has finished the transfer, 
     * the file can be removed.
     * @return
     */
    public boolean isTempFile();
    public void setTempFile(boolean tempFile);

	public String getSrcHostname();
	public void setSrcHostname(String srcHostName);

	public String getSrcFilename();
	public void setSrcFilename(String srcFilename);

    /**
     * Absolute filesystem path of where the file is stored locally.
     */
    public String getLocalPath();
    public void setLocalPath(String localPath);

    /**
     * URL path of the file relative to some absolute URL.
     * This value is stored in the database.
     */
    public String getUrlPath();
    public void setUrlPath(String publicPath);

    public Long getUploadTime();
    public void setUploadTime(Long uploadTime);

	public Date getCreatedDate();
	public void setCreatedDate(Date createdDate);

	public Date getRetiredDate();
	public void setRetiredDate(Date retiredDate);

    public Date getUpdatedDate();
    public void setUpdatedDate(Date updatedDate);
}
