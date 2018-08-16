package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private User user;
	
	public String getInfo() {
		return user.toString();
	}
	
    @Autowired
    private UserMapper userMapper;

    public User findUser(String userId) {

        return userMapper.findByUiId(userId);
    }
	
	
}
