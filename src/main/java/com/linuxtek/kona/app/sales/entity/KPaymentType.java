package com.linuxtek.kona.app.sales.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KPaymentType implements KEnumObject {

    CASH(100L, "Cash"),
    CHECK(200L, "Check"),
    CARD(300L, "Credit/Debit Card"),
    WIRE(400L, "Wire Transfer"),
    ACH(500L, "ACH Transfer"),
    PAYPAL(600L, "PayPal"),
    CREDIT(700L, "Merchant Credit"),
    PROMO(800L, "Promotion/Coupon"),
    EXTERNAL(900L, "External/In-App"),
    APPLE_APPSTORE(901L, "Apple AppStore"),
    GOOGLE_PLAY(902L, "Google Play"),
    PARTNER(903L, "Partner"),
    OTHER(999L, "Other");

    // ---------------------------------------------------------------
    
    private Long id;
    private String displayName;
    private boolean enabled = true;


    private KPaymentType(Long id, String displayName) {
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

    public static KPaymentType getInstance(Long id) {
        return KEnumUtil.getInstance(KPaymentType.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KPaymentType.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KPaymentType.class);
    }
}
