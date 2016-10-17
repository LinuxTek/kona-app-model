package com.linuxtek.kona.app.sales.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KPaymentStatus implements KEnumObject {

    SUCCESS(100L, "Success"),
    CARD_INVALID_NUMBER(200L, "Invalid Card Number"),
    CARD_INVALID_MONTH(210L, "Invalid Expiration Month"),
    CARD_INVALID_YEAR(220L, "Invalid Expiration Year"),
    CARD_INVALID_CVC(230L, "Invalid Security Code"),
    CARD_INVALID_ADDRESS(240L, "Invalid Street Address"),
    CARD_INVALID_ZIP(250L, "Invalid Zip Code"),
    CARD_EXPIRED(260L, "Card Expired"),
    CARD_DECLINED(270L, "Card Declined"),
    CARD_MISSING(280L, "Default Card Not Found"),
    ACCOUNT_INVALID(300L, "Invalid Account"),
    ACCOUNT_DISABLED(310L, "Disabled Account"),
    AMOUNT_INVALID(400L, "Invalid Amount"),
    PROCESSOR_ERROR(900L, "Processor Error"),
    SYSTEM_ERROR(999L, "System Error");

    // ---------------------------------------------------------------
    
    private Long id;
    private String displayName;
    private boolean enabled = true;


    private KPaymentStatus(Long id, String displayName) {
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

    public static KPaymentStatus getInstance(Long id) {
        return KEnumUtil.getInstance(KPaymentStatus.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KPaymentStatus.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KPaymentStatus.class);
    }
}
