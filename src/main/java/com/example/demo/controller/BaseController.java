package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.bean.MyPage;
import com.example.demo.bean.PageData;
import com.example.demo.bean.PlugPage;


public class BaseController {
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	private static final String STR1 = "===>";
	
	private static final String STR2 = "=============start=================";
	
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return request;
	}
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到Page
	 */
	public MyPage getPage() {
		return new MyPage(this.getRequest());
	}
	
	/**
	 * 得到Page2
	 */
	public PlugPage getPage2() {
		return new PlugPage(this.getRequest());
	}


	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info(STR1+interfaceName+STR2);
	}
	
	public static void log(Logger logger, String str) {
		logger.info(STR1+str);
	}

	public static void logAfter(Logger logger) {
		logger.info("===>end");
		logger.info("");
	}
	
}
