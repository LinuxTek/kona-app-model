/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linuxtek.kona.app.entity.KEmailGroupAddress;

public class KAbstractEmailGroupAddressService<T extends KEmailGroupAddress,EXAMPLE> 
		extends KAbstractService<T,EXAMPLE>
		implements KEmailGroupAddressService<T> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractEmailGroupAddressService.class);

	@Autowired
	private EmailAddressService emailAddressService;

	@Autowired
	private EmailGroupAddressMapper emailGroupAddressDao;


	@Override @Transactional
	public EmailGroupAddress add(EmailGroupAddress emailGroupAddress) {
		validate(emailGroupAddress);
		emailGroupAddressDao.insert(emailGroupAddress);
		return emailGroupAddress;
	}

	@Override @Transactional
	public void remove(EmailGroupAddress emailGroupAddress) {
		emailGroupAddressDao.deleteByPrimaryKey(emailGroupAddress.getId());
	}

	@Override @Transactional
	public void removeById(Long emailGroupAddressId) {
		emailGroupAddressDao.deleteByPrimaryKey(emailGroupAddressId);
	}

	@Override
	public void validate(EmailGroupAddress emailGroupAddress) {
		if (emailGroupAddress.getCreatedDate() == null) {
			emailGroupAddress.setCreatedDate(new Date());
		}
	}

	@Override @Transactional
	public EmailGroupAddress update(EmailGroupAddress emailGroupAddress) {
		validate(emailGroupAddress);
		emailGroupAddressDao.updateByPrimaryKey(emailGroupAddress);
		return emailGroupAddress;
	}

	// ----------------------------------------------------------------------

	@Override
	public EmailGroupAddress fetchById(Long emailGroupAddressId) {
		EmailGroupAddress emailGroupAddress = emailGroupAddressDao.selectByPrimaryKey(emailGroupAddressId);
		return emailGroupAddress;
	}

	// ----------------------------------------------------------------------

	public List<EmailGroupAddress> fetchByCriteria(
			Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filterCriteria, boolean distinct) {
		logger.debug("EmailGroupAddressServiceImpl fetch(): called");
		EmailGroupAddressExample example = new EmailGroupAddressExample();

		if (sortOrder != null) {
			example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
		}

		if (startRow == null) startRow = 0;
		if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
        example.setDistinct(distinct);

		KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filterCriteria);
		List<EmailGroupAddress> emailGroupAddressList  = emailGroupAddressDao.selectJoinByExample(example);

		KResultList<EmailGroupAddress> resultList = new KResultList<EmailGroupAddress>();
		resultList.setStartIndex(startRow);
		resultList.setTotalSize(emailGroupAddressList.size());
		resultList.setEndIndex(startRow + emailGroupAddressList.size());

		logger.debug("fetch(): record count: " + emailGroupAddressList.size());

		for (EmailGroupAddress emailGroupAddress : emailGroupAddressList) {
			resultList.add(emailGroupAddress);
		}

		return resultList;
	}

	// ----------------------------------------------------------------------

	@Override
	public EmailGroupAddress fetchByGroupIdAndAddressId(Long groupId, Long addressId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
		filter.put("addressId", addressId);
		List<EmailGroupAddress> result = fetchByCriteria(0, 99999, null, filter, false);
		return KMyBatisUtil.fetchOne(result);
	}

	// ----------------------------------------------------------------------

	@Override
	public List<EmailGroupAddress> fetchByGroupId(Long groupId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	// ----------------------------------------------------------------------

	@Override
	public List<EmailGroupAddress> fetchByAddressId(Long addressId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("addressId", addressId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}
}

