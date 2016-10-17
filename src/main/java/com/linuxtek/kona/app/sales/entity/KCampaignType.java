package com.linuxtek.kona.app.sales.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KCampaignType implements KEnumObject {
    // ---------------------------------------------------------------

    BLOG(100L, "Blog Post"),
    EMAIL(200L, "Email Blast"),
    GOOGLE(300L, "Google Ad"),
    FACEBOOK(400L, "Facebook Ad"),
    TWITTER(500L, "Twitter Ad"),
    BANNER(600L, "Banner Ad"),
    SOCIAL_DEAL(700L, "Social Deal"),
    LANDING_PAGE(800L, "Landing Page"),
    OTHER(999L, "Other");

    // ---------------------------------------------------------------
    
    private Long id;
    private String displayName;
    private boolean enabled = true;

    private KCampaignType(Long id) {
        this(id, null);
    }

    private KCampaignType(Long id, String displayName) {
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

    public static KCampaignType getInstance(Long id) {
        return KEnumUtil.getInstance(KCampaignType.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KCampaignType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KCampaignType.class);
    }
}
