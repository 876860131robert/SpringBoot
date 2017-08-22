package com.robert;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.robert.common.Constants;
import com.robert.filter.SimpleCORSFilter;

@SpringBootApplication
public class Application extends SpringBootServletInitializer 
{
	/**
	 * 配置logger日志
	 */
	private static final Logger logger = Logger.getLogger(Application.class); 
	 @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    static {
        try{
           // 初始化log4j
           String log4jPath = "";
           if(Constants.LOG_MODE == "1"){
               // 配置线上地址
               log4jPath = Application.class.getClassLoader().getResource("").getPath()+"pms-api-services/config/log4j.properties";
               logger.info("Log4j线上生产模式初始化。。。");    
           }else{
               // 配置本地地址
               log4jPath = Application.class.getClassLoader().getResource("").getPath()+"log4j.properties";
               logger.info("Log4j线下开发模式初始化。。。");    
           }                   
           logger.info("初始化Log4j。。。。"); 
           logger.info("path is "+ log4jPath);  
           PropertyConfigurator.configure(log4jPath);// 读取使用Java的特性文件编写的配置文件
        }catch (Exception e){             
              logger.error(e.toString());   
        }
    }
    
    /**
     * SimpleCORSFilter
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SimpleCORSFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("SimpleCORSFilter");
        registration.setOrder(1);
        return registration;
    }
    /**
     * 执行入口
     * 
     * @param args
     */
    public static void main(String[] args) 
    {
        SpringApplication.run(Application.class, args);
    }

}
