/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.social.service;

import java.util.List;

import com.linuxtek.kona.app.social.entity.KAddressBook;

import com.linuxtek.kona.data.service.KDataService;
import com.linuxtek.kona.remote.service.KService;


public interface KAddressBookService<ADDRESSBOOK extends KAddressBook> extends KService, KDataService<ADDRESSBOOK> {
    public static final String SERVICE_PATH = "rpc/kona/AddressBookService";

    public ADDRESSBOOK create(Long userId, String mobileNumber, String email, String firstName, String lastName);
    
    public List<ADDRESSBOOK> fetchByEmail(Long userId, String email);

    public List<ADDRESSBOOK> fetchByMobileNumber(Long userId, String mobileNumber);

    public List<ADDRESSBOOK> fetchByExample(ADDRESSBOOK example);
    
    public List<ADDRESSBOOK> fetchByUserId(Long userId, boolean uninvitedOnly);

    public List<ADDRESSBOOK> saveBatch(List<ADDRESSBOOK> list);
}
