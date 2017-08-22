package com.robert.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.robert.utils.PropertyUtil;

/** 
 * @Title        CommonController.java
 * @Description: 公共Controller
 * @author:      Administrator
 * @date:        2017年8月16日下午4:45:05 
 *  
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {
	/**
	 * 根据key获取slightServerURL.propertise文件中的值
	 * @param tag
	 * @return
	 */
	@RequestMapping("/findPropertiseVal")
	public void findPropertiseVal(HttpServletRequest request, HttpServletResponse response){
		String p_key = request.getParameter("p_key");
		String p_val = "";
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
	    try {
			 p_val = propertyUtil.getVal(p_key);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("p_val",  p_val);
		String mapJson = JSON.toJSONString(map);
		try {
			response.getWriter().write(mapJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}