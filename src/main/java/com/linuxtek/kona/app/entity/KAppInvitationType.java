package com.linuxtek.kona.app.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KAppInvitationType implements KEnumObject {
    // ---------------------------------------------------------------

    JOIN(100L, "Join"),
    FRIEND(200L, "Friend");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KAppInvitationType(Long id) {
        this(id, null);
    }

    private KAppInvitationType(Long id, String displayName) {
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

    public static KAppInvitationType getInstance(Long id) {
        return KEnumUtil.getInstance(KAppInvitationType.class, id);
    }

    public static KAppInvitationType getInstance(String name) {
        return KEnumUtil.getInstance(KAppInvitationType.class, name);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KAppInvitationType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KAppInvitationType.class);
    }
}
