package com.linuxtek.kona.app.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KInvitationType implements KEnumObject {
    // ---------------------------------------------------------------

    JOIN(100L, "Join"),
    FRIEND(200L, "Friend");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KInvitationType(Long id) {
        this(id, null);
    }

    private KInvitationType(Long id, String displayName) {
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

    public static KInvitationType getInstance(Long id) {
        return KEnumUtil.getInstance(KInvitationType.class, id);
    }

    public static KInvitationType getInstance(String name) {
        return KEnumUtil.getInstance(KInvitationType.class, name);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KInvitationType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KInvitationType.class);
    }
}
