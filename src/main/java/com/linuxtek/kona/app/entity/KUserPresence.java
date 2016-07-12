package com.linuxtek.kona.app.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;


public enum KUserPresence implements KEnumObject {
    // ---------------------------------------------------------------

    ONLINE(100L, "Online"),
    AWAY(200L, "Away"),
    BUSY(300L, "Busy"),
    INVISIBLE(400L, "Invisible"),
    STREAMING(500L, "Streaming"),
    OFFLINE(999L, "Offline");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KUserPresence(Long id) {
        this(id, null);
    }

    private KUserPresence(Long id, String displayName) {
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

    public static KUserPresence getInstance(Long id) {
        return KEnumUtil.getInstance(KUserPresence.class, id);
    }
    
    public static KUserPresence getInstance(String name) {
        return KEnumUtil.getInstance(KUserPresence.class, name);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KUserPresence.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        String s = "";
        LinkedHashMap<String,String> map = 
            KEnumUtil.getStringMap(KUserPresence.class);

        for (String key : map.keySet()) {
            s += "key: " + key + "      value: " + map.get(key) + "\n";
        }
        //return KEnumUtil.getStringMap(KUserPresence.class);
        return map;
    }
}
