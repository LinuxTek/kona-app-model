package com.linuxtek.kona.app.entity;

public interface KUserMedia extends KMediaObject {
    
	boolean isPrimaryPhoto();
	void setPrimaryPhoto(boolean primaryPhoto);
}