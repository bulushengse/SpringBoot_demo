package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.bean.A;
import com.example.demo.bean.B;
import com.example.demo.service.UserService;


@RestController//@Controller和@ResponseBody的合集
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	//在application.properties中配置的自定义参数
	@Value("${my.name}")
	private String name;
	@Value("${com.getValue1}")
	private Integer randomNum;
	
	@Autowired
	private A a;
	
	@Autowired
	private B b;
	
	
	//springboot容器管理bean,两种方法创建bean
	//1.在BeanConfig添加带有@Bean注释的方法,如BeanConfig.java中的getA方法
	//2.在bean类中加@Component注释,如B.java
	@RequestMapping(value="/hello", method=RequestMethod.GET)  
	public ModelAndView hello() { 
		System.out.println("A:"+a.toString());
		System.out.println("B:"+b.toString());
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg","hello word!");
		mv.setViewName("index");
		return mv;  
	}  
	
	
	//src/main/resources下面有两个文件夹，static和templates，
	//static中放静态页面，而templates中放动态页面,
	//静态页面的访问   在MVCConfig中加addResourceHandlers方法，访问地址 http://localhost:8080/index.html 
	//动态页面的访问   在pom.xml配置freemarker组件或Thymeleaf组件即可，
	//spring boot不建议使用JSP，如需，
	//1.在MVCConfig中加viewResolver方法或者在application.properties配置
	//2.src/main/目录下添加/WEB-INF/jsp/文件夹
	@RequestMapping(value="/templates", method=RequestMethod.GET)  
	public ModelAndView templates() { 
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg","templates");
		mv.setViewName("hello");
		return mv;  
	}
	
	//@RequestMapping(value="/demo", method=RequestMethod.GET)  
	@GetMapping("/demo")
	//@PostMapping("/demo")
	public String demo() { 
		return "hello word!";   
	}
	
	

}
