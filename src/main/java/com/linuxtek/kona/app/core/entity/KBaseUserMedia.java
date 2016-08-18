package com.linuxtek.kona.app.core.entity;

/**
 * KBaseUserMedia.
 */
public class KBaseUserMedia extends KBaseMediaObject implements KUserMedia {
    private static final long serialVersionUID = 1L;

    private boolean primaryPhoto;
	
    @Override
	public boolean isPrimaryPhoto() {
        return primaryPhoto;
    }

    @Override
	public void setPrimaryPhoto(boolean primaryPhoto) {
        this.primaryPhoto = primaryPhoto;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", uid=").append(getUid());
        sb.append(", parentId=").append(getParentId());
        sb.append(", thumbnailId=").append(getThumbnailId());
        sb.append(", userId=").append(getUserId());
        sb.append(", fileId=").append(getFileId());
        sb.append(", fileTypeId=").append(getFileTypeId());
        sb.append(", urlPath=").append(getUrlPath());
        sb.append(", latitude=").append(getLatitude());
        sb.append(", longitude=").append(getLongitude());
        sb.append(", floor=").append(getFloor());
        sb.append(", description=").append(getDescription());
        sb.append(", enabled=").append(isEnabled());
        sb.append(", width=").append(getWidth());
        sb.append(", height=").append(getHeight());
        sb.append(", bitsPerPixel=").append(getBitsPerPixel());
        sb.append(", framesPerSecond=").append(getFramesPerSecond());
        sb.append(", resizeable=").append(isResizeable());
        sb.append(", primaryPhoto=").append(primaryPhoto);
        sb.append(", createdDate=").append(getCreatedDate());
        sb.append(", lastUpdated=").append(getLastUpdated());
        sb.append("]");
        return sb.toString();
    }


}
