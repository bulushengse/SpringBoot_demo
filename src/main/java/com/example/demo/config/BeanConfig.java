package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.bean.B;
import com.example.demo.bean.User;
import com.example.demo.bean.A;

@Configuration //@Configuration注解可以用java代码的形式实现spring中xml配置文件配置的效果。在方法名前加@Bean
public class BeanConfig {
	
	@Bean(name="A")
	public A getA() {
		return new A();
	}
	
	@Bean(name="user")
	public User getUser() {
		return new User();
	}
}
