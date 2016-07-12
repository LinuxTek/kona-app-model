package com.linuxtek.kona.app.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KFileAccess implements KEnumObject {
    SYSTEM(100L, "System"),
    OWNER(200L, "Owner"),
    FRIEND(300L, "Friend"),
    APP(400L, "App"),
    PUBLIC(500L, "Public"),
    NONE(999L, "Not Accessible");


    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KFileAccess(Long id) {
        this(id, null);
    }

    private KFileAccess(Long id, String displayName) {
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
    
    public static KFileAccess getInstance(Long id) {
        return KEnumUtil.getInstance(KFileAccess.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KFileAccess.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KFileAccess.class);
    }
}
