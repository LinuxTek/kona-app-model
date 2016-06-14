/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.entity.KEntityNameRule;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractEntityNameRuleService<E extends KEntityNameRule,EEXAMPLE> 
extends KAbstractService<E,EEXAMPLE>
implements KEntityNameRuleService<E> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractEntityNameRuleService.class);
	
	private List<E> rules = null;

	// ----------------------------------------------------------------------------
	
	public void validate(E data) {
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public E fetchByPattern(String pattern) {
		E rule = null;
		Map<String,Object> filter = KMyBatisUtil.createFilter("pattern", pattern);
		List<E> list = fetchByCriteria(0,9999, null, filter,  false);
		if (list != null && list.size() == 1) {
			rule = list.get(0);
		}
		return rule;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public E fetchForName(String name) {
		if (rules == null) {
			rules = fetchAll();
		}

		logger.debug("EntityNameRuleService: checking rule match for name: " + name);
		for (E rule : rules) {
			String pattern = rule.getPattern();
			//logger.debug("UsernameRuleService: checking rule pattern: " + pattern);

			Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			Matcher m = p.matcher(name);
			if (m.matches()) {
				logger.debug("UsernameRuleService: matched pattern: " + pattern);
				return rule;
			}
		}

		logger.debug("UsernameRuleService: no pattern matched.");
		return null;
	}

	// ----------------------------------------------------------------------------
	
	@Override
	public List<E> fetchAll() {
		return fetchByCriteria(0, 999999, null, null,  false);
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public boolean isReserved(String name) {
		E rule = fetchForName(name);
		if (rule != null) {
			return rule.isReserved();
		}
		return false;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public boolean isBlackListed(String name) {
		E rule = fetchForName(name);
		if (rule != null) {
			return rule.isBlackListed();
		}
		return false;
	}
	
	// ----------------------------------------------------------------------------

	@Override
	public boolean isAcceptable(String name) {
		E rule = fetchForName(name);
		if (rule != null) {
			return !rule.isBlackListed() && !rule.isReserved();
		}
		return true;
	}

}
