package com.robert.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.robert.common.Constants;
import com.robert.service.LoginService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
	/**
	 * 日志dsdsds
	 */
	private static final Logger              logger    = Logger.getLogger(LoginController.class);
    @Autowired
	LoginService loginService;
	@RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
		
		logger.info("");
			String userName=request.getParameter("userName");
			String passWord=request.getParameter("passWord");
			Map<String, String> map = new HashMap<String, String>();
			if(userName=="123" && passWord=="123"){
				map.put("msg", "登录成功！");
				map.put("result", Constants.success);
			}else{
				map.put("msg", "删除失败！");
				map.put("result", Constants.fail);
			}
			String mapJson = JSON.toJSONString(map);
			return mapJson;
	
  } 
	
	@RequestMapping(value = "/count",method = RequestMethod.GET)
    public void count(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		logger.info("记录广告点击次数开始！！");
		String times=request.getParameter("count");
		//StringUtils在commons-lang-2.2.jar包中：org.apache.commons.lang.StringUtils
		if(StringUtils.isBlank(times)&&StringUtils.isEmpty(times)){
			logger.warn("入参为空，请检查是否请求到后台times="+times);
		}
			loginService.saveTimes(times);
			System.out.println(times);
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg", "统计次数成功！");
			map.put("result", Constants.success);
			try {
				response.getWriter().write(JSON.toJSONString(map));
				logger.info("记录广告点击次数结束！！");
			} catch (IOException e) {
				logger.warn("内部错误！",e);
			}
  } 
	
}
