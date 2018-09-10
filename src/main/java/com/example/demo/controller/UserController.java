package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.bean.PageData;
import com.example.demo.bean.PlugPage;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;

@RestController
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/findUser", method=RequestMethod.GET)  
	public ModelAndView findUser() { 
		ModelAndView mv = new ModelAndView();
		User user = userService.findUser("111111");
		mv.addObject("msg",user.toString());
		mv.setViewName("index");
		return mv;  
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.GET)  
	public ModelAndView saveUser() { 
		ModelAndView mv = new ModelAndView();
		User user = new User();
		user.setUSER_ID("111111");
		user.setIP("");
		user.setLAST_LOGIN("");
		user.setNAME("");
		user.setUSERNAME("");
		user.setPASSWORD("");
		userService.saveUser(user);
		mv.addObject("msg","ok");
		mv.setViewName("index");
		return mv;  
	} 
	
	
	/**
	 * springboot 分页的使用 两种方式         
	 * 1.使用PageHelper分页   PlugPage.java
	 * 
	 * 2.使用自定义分页    MyPage.java
	 * 
	 * @return
	 */
	@RequestMapping(value="/listPageUser1", method=RequestMethod.GET)  
	public ModelAndView listPageUser1() { 
		
		PageData pd = this.getPageData();
		PlugPage plugPage = this.getPage2();
		ModelAndView mv = new ModelAndView();
		
		plugPage.setPd(pd);     //添加sql语句条件参数
		
		List<User> users= userService.datalistPage(plugPage); //列出USER表
		
		PageInfo<User> pageinfo = new PageInfo<User>(users);  //包装page类
		
		mv.addObject("pageinfo",pageinfo);
		mv.setViewName("pageDemo");
		return mv;  
	}
	
	//2.使用自定义分页    MyPage.java  (待开发)
	@RequestMapping(value="/listPageUser2", method=RequestMethod.GET)  
	public ModelAndView listPageUser2() { 
		
		PageData pd = this.getPageData();
		PlugPage plugPage = this.getPage2();
		ModelAndView mv = new ModelAndView();
		
		plugPage.setPd(pd);     //添加sql语句条件参数
		
		List<User> users= userService.datalistPage(plugPage); //列出USER表
		
		PageInfo<User> pageinfo = new PageInfo<User>(users);  //包装page类
		
		mv.addObject("pageinfo",pageinfo);
		mv.setViewName("pageDemo");
		return mv;  
	}

}
