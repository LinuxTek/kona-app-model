package com.linuxtek.kona.app.comm.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KEmailEventType implements KEnumObject {
    // ---------------------------------------------------------------

    ATTEMPTED(100L, "Attempted"),
    FAILED(110L, "Failed"),
    DELIVERED(200L, "Delivered"),
    BOUNCED(300L, "Bounced"),
    COMPLAINED(400L, "Complained"),
    UNSUBSCRIBED(500L, "Unsubscribed"),
    OPENED(600L, "Opened"),
    FORWARDED(700L, "Forwarded"),
    PRINTED(800L, "Printed"),
    CLICKED(900L, "Clicked");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KEmailEventType(Long id) {
        this(id, null);
    }

    private KEmailEventType(Long id, String displayName) {
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

    // ---------------------------------------------------------------

    public static KEmailEventType getInstance(Long id) {
        return KEnumUtil.getInstance(KEmailEventType.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KEmailEventType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KEmailEventType.class);
    }
}
