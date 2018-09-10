package com.example.demo.bean;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.github.pagehelper.Page;

public class PlugPage {

	private Integer pageNum = 1;
	
	//private Page page;
	
	private PageData pd = new PageData();

	public PlugPage(HttpServletRequest req) {
		
		if(req.getParameter("pageNum") != null) {
			pageNum = Integer.valueOf(req.getParameter("pageNum"));
		}
		
		//page = new Page(pageNum, pageSize);
	}

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	
	
	
}
