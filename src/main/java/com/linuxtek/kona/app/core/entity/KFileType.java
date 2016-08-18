package com.linuxtek.kona.app.core.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;
import com.linuxtek.kona.http.KMimeTypes;


public enum KFileType implements KEnumObject {

    FOLDER(100L, "Folder"),
    THUMBNAIL(200L, "Thumbnail"),
    IMAGE(300L, "Image"),
    AUDIO(400L, "Audio"),
    VIDEO(500L, "Video"),
    DOCUMENT(600L, "Document"),
    ARCHIVE(700L, "Archive"),
    EXECUTABLE(800L, "Executable"),
    OTHER(999L, "Other");


    // ---------------------------------------------------------------
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KFileType(Long id) {
        this(id, null);
    }

    private KFileType(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name();
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return KEnumUtil.toString(this);
    }
    
    public static KFileType getInstance(String contentType, boolean defaultType) {
    	KFileType type = getInstance(contentType);
    	if (type == null && defaultType) {
    		type = KFileType.OTHER;
    	}
    	return type;
    }
    
    public static KFileType getInstance(String contentType) {
    	if (contentType == null) return null;
    	
    	for (String type : KMimeTypes.IMAGE_MIME_TYPES) {
    		if (contentType.equalsIgnoreCase(type)) {
    			return KFileType.IMAGE;
    		}
    	}
        
    	for (String type : KMimeTypes.AUDIO_MIME_TYPES) {
    		if (contentType.equalsIgnoreCase(type)) {
    			return KFileType.AUDIO;
    		}
    	}
        
    	for (String type : KMimeTypes.VIDEO_MIME_TYPES) {
    		if (contentType.equalsIgnoreCase(type)) {
    			return KFileType.VIDEO;
    		}
    	}
        
    	for (String type : KMimeTypes.DOCUMENT_MIME_TYPES) {
    		if (contentType.equalsIgnoreCase(type)) {
    			return KFileType.VIDEO;
    		}
    	}
        
    	for (String type : KMimeTypes.ARCHIVE_MIME_TYPES) {
    		if (contentType.equalsIgnoreCase(type)) {
    			return KFileType.VIDEO;
    		}
    	}
    	
    	if (contentType.equalsIgnoreCase("application/octet-stream")) {
    		return KFileType.OTHER;
    	}
    	
        return null;
    }

    public static KFileType getInstance(Long id) {
        return KEnumUtil.getInstance(KFileType.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KFileType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KFileType.class);
    }
}
