package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.PageData;
import com.example.demo.bean.PlugPage;
import com.example.demo.bean.User;
import com.github.pagehelper.Page;


//1、Mapper接口方法名与Mapper.xml(UserMapper.xml)中定义的statement的id相同
//2、Mapper接口方法的输入参数类型和mapper.xml（User.xml）中定义的statement的parameterType的类型相同
//3、Mapper接口方法的输出参数类型和mapper.xml中定义的statement的resultType的类型相同
@Mapper
public interface UserMapper {

	User findByUiId(String userId);
	
	Integer save(User user);
	
	List<User> datalistPage(PlugPage plugPage);
	
}
