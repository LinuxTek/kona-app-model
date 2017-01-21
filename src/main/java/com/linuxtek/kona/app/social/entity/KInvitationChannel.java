package com.linuxtek.kona.app.social.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KInvitationChannel implements KEnumObject {
    // ---------------------------------------------------------------

    IN_APP(100L, "In App"),
    EMAIL(200L, "Email"),
    SMS(300L, "Sms"),
    TWITTER(400L, "Twitter"),
    FACEBOOK(500L, "Facebook");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KInvitationChannel(Long id) {
        this(id, null);
    }

    private KInvitationChannel(Long id, String displayName) {
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

    public static KInvitationChannel getInstance(Long id) {
        return KEnumUtil.getInstance(KInvitationChannel.class, id);
    }

    public static KInvitationChannel getInstance(String name) {
        return KEnumUtil.getInstance(KInvitationChannel.class, name);
    } 

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KInvitationChannel.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KInvitationChannel.class);
    }
}
