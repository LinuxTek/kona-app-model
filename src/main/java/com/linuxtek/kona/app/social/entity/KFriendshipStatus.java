package com.linuxtek.kona.app.social.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KFriendshipStatus implements KEnumObject {
    NONE(100L, "No Friendship"), // state after unfollow, unblock, etc.
    FRIENDS(200L, "Friendship Established"),
    PENDING(300L, "Friendship Pending"), // invited 
    FOLLOWING(400L, "Following Friend"),
    FOLLOWED(500L, "Followed By Friend"),
    BLOCKING(600L, "Blocking Friend"),
    BLOCKED(700L, "Blocked By Friend");

    // ---------------------------------------------------------------
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KFriendshipStatus(Long id) {
        this(id, null);
    }

    private KFriendshipStatus(Long id, String displayName) {
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

    public static KFriendshipStatus getInstance(Long id) {
        return KEnumUtil.getInstance(KFriendshipStatus.class, id);
    }

    public static KFriendshipStatus getInstance(String name) {
        return KEnumUtil.getInstance(KFriendshipStatus.class, name);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KFriendshipStatus.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KFriendshipStatus.class);
    }
}
