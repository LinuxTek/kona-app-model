/*
 * Copyright (C) 2013 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.app.core.entity;

import java.util.Date;

/**
 * KBaseApiVersion
 */
public class KBaseApiVersion implements KApiVersion {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String description;
    private boolean enabled;
    private Date publishedDate;
    private Date createdDate;
    private Date updatedDate;


    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
	public String getDescription() {
        return description;
    }

    @Override
	public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
	public boolean isEnabled() {
        return enabled;
    }

    @Override
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
	public Date getPublishedDate() {
        return publishedDate;
    }

    @Override
	public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
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
	public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", enabled=").append(enabled);
        sb.append(", publishedDate=").append(publishedDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
