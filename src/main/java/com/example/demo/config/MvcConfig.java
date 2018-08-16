package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.example.demo.Interceptors.DemoInterceptors;

@Configuration  
@EnableWebMvc   //开启Spring MVC支持，若无此句，重写WebMvcConfigurerAdapter方法无效  
//@ComponentScan("com.example.demo")    //扫描组件
public class MvcConfig extends WebMvcConfigurerAdapter{
		
	  /**
	   * 控制层controller返回view添加设置前缀 和后缀，两种方法
	   * 1.手动创建viewResolver()
	   * 2.在application.properties文件中配置
	   * @return
	   */
	   @Bean  
	   public InternalResourceViewResolver viewResolver(){  
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();  
	        viewResolver.setPrefix("/WEB-INF/jsp/");  
	        viewResolver.setSuffix(".jsp");  
	        viewResolver.setViewClass(JstlView.class);  
	        return  viewResolver;  
	    } 
	   
	   /**
	    * 对静态资源的访问
	    */
	    @Override  
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
	        //super.addResourceHandlers(registry);  
	        //addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径  
	        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");  
	    }
	    
	   /**
	    * 重写configurePathMatch方法可忽略"."后面的参数
	    */
	    public void configurePathMatch(PathMatchConfigurer configurer) {
			// super.configurePathMatch(configurer);
			configurer.setUseSuffixPatternMatch(false);
		}  
	   
	   /** 
	    * 配置拦截器的Bean 
	    * @return 
	    */  
	    @Bean  
	    public DemoInterceptors demoInterceptor(){  
	        return new DemoInterceptors();  
	    }  
	  
	    /** 
	     * 重写addInterceptors方法，注册拦截器 
	     * @param registry 
	     */  
	    @Override  
	    public void addInterceptors(InterceptorRegistry registry) { 
	    	//super.addInterceptors(registry);  
	        registry.addInterceptor(demoInterceptor());  
	    }  
	   

	   
	   
}