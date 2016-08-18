package com.linuxtek.kona.app.social.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KFriendshipEventType implements KEnumObject {
    FOLLOW(100L, "Follow"),
    UNFOLLOW(200L, "Unfollow"),
    BLOCK(300L, "Block"),
    UNBLOCK(400L, "Unblock"),
    FRIENDSHIP_REQUEST(500L, "Request Friendship"),
    FRIENDSHIP_ACCEPT(600L, "Accept Friendship"),
    FRIENDSHIP_REJECT(700L, "Reject Friendship"),
    FRIENDSHIP_REVOKE(800L, "Revoke Friendship");

    // ---------------------------------------------------------------
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KFriendshipEventType(Long id) {
        this(id, null);
    }

    private KFriendshipEventType(Long id, String displayName) {
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

    public static KFriendshipEventType getInstance(Long id) {
        return KEnumUtil.getInstance(KFriendshipEventType.class, id);
    }

    public static KFriendshipEventType getInstance(String name) {
        return KEnumUtil.getInstance(KFriendshipEventType.class, name);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KFriendshipEventType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KFriendshipEventType.class);
    }
}
