package com.zero.newsdemo.beans;

/**
 * News object class
 * 
 * @author Bane
 * 
 */
public class News {
	private String mName;
	private String mActionUrl;
	private String mImageUrl;
	private int mImageResourceId = -1;
	private final boolean useLocalImage;

	public News(String mName, String mActionUrl, String mImageUrl) {
		super();
		this.mName = mName;
		this.mActionUrl = mActionUrl;
		this.mImageUrl = mImageUrl;
		useLocalImage = false;
	}

	public News(String mName, String mActionUrl, int mImageResourceId) {
		super();
		this.mName = mName;
		this.mActionUrl = mActionUrl;
		this.mImageResourceId = mImageResourceId;
		useLocalImage = true;
	}

	public String getName() {
		return mName;
	}

	public String getActionUrl() {
		return mActionUrl;
	}

	public String getImageUrl() {
		return mImageUrl;
	}

	public int getImageResourceId() {
		return mImageResourceId;
	}

	public boolean isUseLocalImage() {
		return useLocalImage;
	}
}
