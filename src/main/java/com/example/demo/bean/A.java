package com.example.demo.bean;


import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;


@Setter@Getter
public class A {
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	
	
}
