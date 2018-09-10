package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@RestController
public class NotFoundController implements ErrorController{

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		 return "/error";
	}
	
	@RequestMapping(value = "/error", produces = "text/html")
	public Object errorHtml(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("404");
		return mv;
	}
	
	@RequestMapping(value = "/error")
	@ResponseBody
	public Object error(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("404");
		return mv;
	}

}
