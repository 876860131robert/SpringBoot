package com.robert.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/** 
 * @Title        SimpleCORSFilter.java
 * @Description: 针对跨域过滤器
 * @author:      Administrator
 * @date:        2017年8月8日下午2:21:27 
 *  
 */
public class SimpleCORSFilter implements Filter{
	/** 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Access-Control-Allow-Origin 为允许哪些Origin发起跨域请求. 这里设置为"*"表示允许所有，通常设置为所有并不安全，最好指定一下。
    Access-Control-Allow-Methods 为允许请求的方法.
    Access-Control-Max-Age 表明在多少秒内，不需要再发送预检验请求，可以缓存该结果
    Access-Control-Allow-Headers 表明它允许跨域请求包含content-type头，这里设置的x-requested-with ，表示ajax请求
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Headers", "content-type, token");
        response.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(req, res);

    }

    /** 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
}
}
