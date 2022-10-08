package com.cc.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public String test() {
		return "ok";
	}
	
	@RequestMapping("/findByUsername")
	public List<User> findByUsername(String username) throws Exception {
		//定义页面所需权限
		userService.setRole("12");

		return userService.findByUsername(username);
	}
}
