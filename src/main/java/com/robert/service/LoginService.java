package com.robert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robert.dao.LoginDao;
import com.robert.model.AdvertModel;
import com.robert.utils.DateUtils;

/** 
 * @Title        LoginService.java
 * @Description: 广告统计次数的业务类
 * @author:      Administrator
 * @date:        2017年8月15日上午11:54:40 
 *  
 */
@Service
@Transactional
public class LoginService {
	@Autowired
	LoginDao loginDao;
	
	public void saveTimes(String times){
		AdvertModel advertModel=new AdvertModel();
		advertModel.setAd_id(1);
		advertModel.setClickCount(Integer.valueOf(times));//字符串转int类型
		advertModel.setDate(DateUtils.getCurrentTime());
		advertModel.setLastViewDateTime(DateUtils.getCurrentTime());//获取系统的当期时间
		advertModel.setLastClickDateTime(DateUtils.getCurrentTime());
		advertModel.setViewCount(Integer.valueOf(times));
		loginDao.save(advertModel);
	}

}
