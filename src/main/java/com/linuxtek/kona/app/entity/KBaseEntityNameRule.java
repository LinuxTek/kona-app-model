/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.entity;

import java.util.Date;

/**
 * KBaseEntityNameRule.
 */
public class KBaseEntityNameRule implements KEntityNameRule {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String pattern;
    private boolean blackListed;
    private boolean reserved;
    private Date createdDate;
    private Date lastUpdated;

    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public String getPattern() {
        return pattern;
    }

    @Override
	public void setPattern(String pattern) {
        this.pattern = pattern == null ? null : pattern.trim();
    }
    
    @Override
	public boolean isBlackListed() {
        return blackListed;
    }

    @Override
	public void setBlackListed(boolean blackListed) {
        this.blackListed = blackListed;
    }

    @Override
	public boolean isReserved() {
        return reserved;
    }

    @Override
	public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @Override
	public Date getLastUpdated() {
        return lastUpdated;
    }

    @Override
	public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pattern=").append(pattern);
        sb.append(", blackListed=").append(blackListed);
        sb.append(", reserved=").append(reserved);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append("]");
        return sb.toString();
    }
}
