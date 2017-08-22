package com.robert.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @Title        AdvertModel.java
 * @Description: 广告计数表modle
 * @author:      Administrator
 * @date:        2017年8月15日上午11:31:21 
 *  
 */
public class AdvertModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5251466988068034018L;
	
	private int id;//广告id主键
	private int ad_id;//外键
	private String date;//
	private int viewCount;//显示次数
	private int clickCount;//点击次数
	private String lastViewDateTime;//最后一次显示时间
	private String lastClickDateTime;//最后一次点击时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public String getLastViewDateTime() {
		return lastViewDateTime;
	}
	public void setLastViewDateTime(String lastViewDateTime) {
		this.lastViewDateTime = lastViewDateTime;
	}
	public String getLastClickDateTime() {
		return lastClickDateTime;
	}
	public void setLastClickDateTime(String lastClickDateTime) {
		this.lastClickDateTime = lastClickDateTime;
	}
	@Override
	public String toString() {
		return "AdvertModel [id=" + id + ", ad_id=" + ad_id + ", date=" + date + ", viewCount=" + viewCount
				+ ", clickCount=" + clickCount + ", lastViewDateTime=" + lastViewDateTime + ", lastClickDateTime="
				+ lastClickDateTime + "]";
	}

}
