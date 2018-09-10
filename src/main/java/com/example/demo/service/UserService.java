package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.PlugPage;
import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import com.github.pagehelper.PageHelper;

@Service
public class UserService{
	
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
    
    //@Transactional
    //@Transactional(value="transactionManager1") 
    public void saveUser(User user) {

    	userMapper.save(user);
    	
    	if (true) {
            throw new RuntimeException("save 抛异常了");
        }
    }
	
	@Value("${pageSize}")
	private Integer pageSize;   //每页条数在配置文件中配置
	
    public List<User> datalistPage(PlugPage plugPage) {
    	
    	PageHelper.startPage(plugPage.getPageNum(),pageSize); //这句代码使分页插件生效
    	
        return userMapper.datalistPage(plugPage);
    }

	
}
