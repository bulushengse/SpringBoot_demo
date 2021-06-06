package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest    //测试类必须要与启动类SpringBootDemoApplication同一包名下
public class SpringBootDemoApplicationTests {

	
	@Autowired
	private UserService userService;
	
	@Test
	public void contextLoads() {
		
		User user = userService.findUser("1");
		
		System.out.println(user.toString());
	}

}
