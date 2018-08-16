package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)  
	public ModelAndView hello() { 
		ModelAndView mv = new ModelAndView();
		User user = userService.findUser("1");
		mv.addObject("msg",user.toString());
		mv.setViewName("index");
		return mv;  
	}  
}
