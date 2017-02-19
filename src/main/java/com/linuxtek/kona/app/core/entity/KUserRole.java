package com.linuxtek.kona.app.core.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;
import com.linuxtek.kona.util.KStringUtil;

/*
 * NOTE: This enum is used as a bit field for User.roles
 * so all values must be powers of 2.
 */

public enum KUserRole implements KEnumObject {
    // ---------------------------------------------------------------

    SYSTEM(1L, "System"),
    ADMIN(2L, "Admin"),
    USER(4L, "User"),
    GUEST(8L, "Guest");

    // ---------------------------------------------------------------
    
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KUserRole(Long id) {
        this(id, null);
    }

    private KUserRole(Long id, String displayName) {
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
    
    public static Long toLong(String s) {
        Long result = 0L;
        String[] values = s.split(",");
        for (String value : values) {
            KUserRole role = KUserRole.valueOf(value.toUpperCase());
            result = result | role.getId();
        }
        return result;
    }
    
    public static Long toLong(List<KUserRole> roleList) {
        Long result = 0L;
        for (KUserRole role : roleList) {
            result = result | role.getId();
        }
        return result;
    }

    public static List<KUserRole> parse(String s) {
        String[] values = s.split("\\s*,\\s*");

        List<KUserRole> result = new ArrayList<KUserRole>();

        for (String value : values) {
            //KUserRole role = KUserRole.valueOf(value.toUpperCase());
            KUserRole role = getInstance(value);
            result.add(role);
        }

        return result;
    }
    
    public static List<KUserRole> parse(Long roles) {
        List<KUserRole> result = new ArrayList<KUserRole>();

        KUserRole[] values = KUserRole.class.getEnumConstants();

        for (KUserRole role : values) {
            if ((roles & role.getId()) != 0) {
                result.add(role);
            }
        }
        
        return result;
    }
    
    public static List<String> toStringList(List<KUserRole> roles) {
        List<String> result = new ArrayList<String>();

        for (KUserRole role : roles) {
            result.add(role.name().toLowerCase());
        }

        return result;
    }
    
    public static List<String> toString(Long roles) {
        return toStringList(parse(roles));
    }
    
    public static boolean haveRole(Long roles, KUserRole role) {
    	List<KUserRole> list = parse(roles);

    	for (KUserRole r : list) {
    		if (r.equals(role)) return true;
    	}

        return false;
    }
    
    public static boolean haveRole(Long roles, String roleName) {
        KUserRole role = getInstance(roleName);

        List<KUserRole> list = parse(roles);

        for (KUserRole r : list) {
            if (r.equals(role)) return true;
        }

        return false;
    }

    public static KUserRole getInstance(Long id) {
        return KEnumUtil.getInstance(KUserRole.class, id);
    }

    public static KUserRole getInstance(String name) {
        return KEnumUtil.getInstance(KUserRole.class, name);
    } 

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KUserRole.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KUserRole.class);
    }
}
