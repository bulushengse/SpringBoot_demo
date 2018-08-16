package com.example.demo.bean;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component  //泛指组件，当组件不好归类时，可以使用这个注解进行标注，该bean注入到IOC容器中
@Setter@Getter   //lombok插件功能，自动创建get、set方法
public class B {
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	
}
