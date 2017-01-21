package com.linuxtek.kona.app.core.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KNotificationChannel implements KEnumObject {
    
    IN_APP(100L, "In-app"),
    EMAIL(200L, "Email"),
    SMS(300L, "SMS"),
    PUSH(400L, "Push Notification");

    // ----------------------------------------------------------------------

    private Long id;
    private String displayName;
    private boolean enabled = true;

    // ----------------------------------------------------------------------

    private KNotificationChannel(Long id) {
        this(id, null);
    }

    private KNotificationChannel(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    // ----------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    // ----------------------------------------------------------------------

    public String getName() {
        return name();
    }

    // ----------------------------------------------------------------------

    public String getDisplayName() {
        return displayName;
    }

    // ----------------------------------------------------------------------

    public boolean isEnabled() {
        return enabled;
    }
    
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return KEnumUtil.toString(this);
    }

    // ----------------------------------------------------------------------

    public static KNotificationChannel getInstance(Long id) {
        return KEnumUtil.getInstance(KNotificationChannel.class, id);
    }

    // ----------------------------------------------------------------------

    public static KNotificationChannel getInstance(String name) {
        return KEnumUtil.getInstance(KNotificationChannel.class, name);
    }

    // ----------------------------------------------------------------------

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KNotificationChannel.class);
    }

    // ----------------------------------------------------------------------

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KNotificationChannel.class);
    }
}
