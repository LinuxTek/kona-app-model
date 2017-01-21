package com.linuxtek.kona.app.core.entity;

import java.util.Date;

public class KBaseAppConfig implements KAppConfig {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long appId;
    private String env;
    private String name;
    private String value;
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
	public Long getAppId() {
        return appId;
    }

    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
	public String getEnv() {
        return env;
    }

    @Override
	public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
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
	public String getValue() {
        return value;
    }

    @Override
	public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
        sb.append(", appId=").append(appId);
        sb.append(", env=").append(env);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append("]");
        return sb.toString();
    }
}
