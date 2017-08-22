package com.robert.dao;

import org.apache.ibatis.annotations.Mapper;

import com.robert.model.AdvertModel;

/**
 * 保存点击次数
 * 
 * @author Administrator
 *
 */
@Mapper
public interface LoginDao {


	/**
	 * @param times
	 */
	public void save( AdvertModel advertModel);

}
