package com.linuxtek.kona.app.core.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KAuthCodeType implements KEnumObject {
    // ---------------------------------------------------------------

    EMAIL_CONFIRMATION(100L, "Email Confirmation"),
    MOBILE_CONFIRMATION(200L, "Mobile Confirmation"),
    PHONE_CONFIRMATION(300L, "Phone Confirmation"), // landline
    PASSWORD_RESET(400L, "Password Reset");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KAuthCodeType(Long id) {
        this(id, null);
    }

    private KAuthCodeType(Long id, String displayName) {
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

    public static KAuthCodeType getInstance(Long id) {
        return KEnumUtil.getInstance(KAuthCodeType.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KAuthCodeType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KAuthCodeType.class);
    }
}
