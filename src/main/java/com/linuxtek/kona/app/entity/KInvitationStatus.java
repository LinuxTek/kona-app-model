package com.linuxtek.kona.app.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KInvitationStatus implements KEnumObject {
    // ---------------------------------------------------------------

    PENDING(100L, "Pending"),
    ACCEPTED(200L, "Accepted"),
    DECLINED(300L, "Declined"),
    IGNORED(400L, "Ignored");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KInvitationStatus(Long id) {
        this(id, null);
    }

    private KInvitationStatus(Long id, String displayName) {
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

    public static KInvitationStatus getInstance(Long id) {
        return KEnumUtil.getInstance(KInvitationStatus.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KInvitationStatus.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KInvitationStatus.class);
    }
}
