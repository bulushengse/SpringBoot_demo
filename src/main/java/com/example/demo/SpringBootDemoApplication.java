package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication：包含了1.@ComponentScan、2.@Configuration和3.@EnableAutoConfiguration注解
//@Component注解把该bean注入到IOC容器中
//1@ComponentScan注解扫描IOC容器里的bean,不指定则默认指扫描当前启动类所在的包里的对象
//@ConfigurationProperties把properties文件转化为bean，
//2@Configuration注解用java代码的形式实现spring中xml配置文件的配置。
//3@EnableConfigurationProperties注解使@ConfigurationProperties生效，并从IOC容器中获取bean
//@MapperScan("com.example.demo.mapper")
@SpringBootApplication 
public class SpringBootDemoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
	
	protected SpringApplicationBuilder config(SpringApplicationBuilder applicationBuilder){
	    return applicationBuilder.sources(SpringBootDemoApplication.class);
	}
}
