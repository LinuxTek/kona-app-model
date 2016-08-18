/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.service;

import java.util.List;

import com.linuxtek.kona.app.core.entity.KEntityNameRule;
import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KEntityNameRuleService<E extends KEntityNameRule> extends KService, KDataService<E> {
    public static final String SERVICE_PATH = "rpc/kona/EntityNameRuleService";
    
    public List<E> fetchAll();
    
    public E fetchByPattern(String pattern);
    
    public E fetchForName(String name);
    
    public boolean isReserved(String name);
    
    public boolean isBlackListed(String name);
    
    public boolean isAcceptable(String name);
    
}
