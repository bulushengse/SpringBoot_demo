package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.bean.User;
import com.example.demo.bean.A;

@Configuration //@Configuration启动容器+@Bean注册Bean
public class BeanConfig {
	
	@Bean(name="A")
	public A getA() {
		return new A();
	}
	
	@Bean(name="user")      //表映射类不需要放容器，只是测试用
	public User getUser() {
		return new User();
	}
}
