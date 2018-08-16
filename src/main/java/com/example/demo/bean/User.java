package com.example.demo.bean;

import lombok.Data;
import lombok.ToString;

@Data  //lombok插件功能,自动创建出来get、set、toString、eaquls、hashCode等方法
public class User {
	
	private String USER_ID;         // 用户id
	private String USERNAME; 		// 用户名
	private String PASSWORD; 		// 密码
	private String NAME; 			// 姓名
	private String RIGHTS; 			// 权限
	private String ROLE_ID; 		// 角色id
	private String LAST_LOGIN; 		// 最后登录时间
	private String IP; 				// 用户登录ip地址
	private String STATUS; 			// 状态
	private String SKIN; 			// 皮肤
	
	
}
