package com.cc.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	List<User> findByUsername(String username){
		return userMapper.findByUsername(username);
	}
	
	public void setRole(String role,HttpServletRequest request) throws Exception {
		//SESSION中的用户名
		Object login = request.getSession().getAttribute("users");
		if(login != null) {
			String username = login.toString();
			//数据库中查询到的角色列表
			List<String> arr = userMapper.getRole(username);
			
			System.out.println("检测得到用户名："+username);
			System.out.println("角色拥有的权限："+arr);
			System.out.println("页面需要的权限："+role);
			
			//如存在匹配的角色则返回true
			boolean roleCheck = arr.contains(role);
			System.out.println(roleCheck);
			
			//如果为fasle则抛出错误
			if(roleCheck == true) {
				System.out.println("允许访问");
			}else {
				throw new Exception("没有对应权限");
			}
		}else {
			throw new Exception("未登录session");
		}

	}
}
