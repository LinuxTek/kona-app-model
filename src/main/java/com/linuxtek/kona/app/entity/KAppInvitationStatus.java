package com.linuxtek.kona.app.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KAppInvitationStatus implements KEnumObject {
    // ---------------------------------------------------------------

    PENDING(100L, "Pending"),
    ACCEPTED(200L, "Accepted"),
    DECLINED(300L, "Declined"),
    IGNORED(400L, "Ignored");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KAppInvitationStatus(Long id) {
        this(id, null);
    }

    private KAppInvitationStatus(Long id, String displayName) {
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

    public static KAppInvitationStatus getInstance(Long id) {
        return KEnumUtil.getInstance(KAppInvitationStatus.class, id);
    }

    public static KAppInvitationStatus getInstance(String name) {
        return KEnumUtil.getInstance(KAppInvitationStatus.class, name);
    } 

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KAppInvitationStatus.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KAppInvitationStatus.class);
    }
}
