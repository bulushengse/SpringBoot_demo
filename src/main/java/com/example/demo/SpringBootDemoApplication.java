package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement //开启事务支持
//@SpringBootApplication：包含了1@ComponentScan 、2@Configuration 和3@EnableAutoConfiguration注解
//@Component注解把该bean注入到IOC容器中
//1@ComponentScan注解扫描IOC容器里的bean,不指定则默认指扫描当前启动类所在的包里的对象
//@ConfigurationProperties把properties文件转化为bean，
//2@Configuration注解用于定义配置类，可替换xml配置文件，内部@Bean注解的方法，构建bean定义，初始化Spring容器
//@EnableAutoConfiguration注解 启动后会加载大量的自动配置类，所有具有META-INF/spring.factories的jar包
//@MapperScan("com.example.demo.mapper")
@SpringBootApplication 
public class SpringBootDemoApplication extends SpringBootServletInitializer{

	/*
	 * 访问地址：http://localhost:8080/
	*/	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
	
	protected SpringApplicationBuilder config(SpringApplicationBuilder applicationBuilder){
	    return applicationBuilder.sources(SpringBootDemoApplication.class);
	}
}
