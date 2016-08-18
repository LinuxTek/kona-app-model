package com.linuxtek.kona.app.core.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


/**
 * Types of tokens that can be issued.
 *
 * BASIC - issued to a client to identify it and allow access to public resources
 * BEARER - issued to a client on behalf of a user to access user specific resources
 */
public enum KTokenType implements KEnumObject {
    BASIC(100L, "Basic"),
    BEARER(200L, "Bearer");

    // ---------------------------------------------------------------
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KTokenType(Long id) {
        this(id, null);
    }

    private KTokenType(Long id, String displayName) {
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

    public static KTokenType getInstance(Long id) {
        return KEnumUtil.getInstance(KTokenType.class, id);
    }

    public static KTokenType getInstance(String name) {
        return KEnumUtil.getInstance(KTokenType.class, name);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KTokenType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KTokenType.class);
    }
}
