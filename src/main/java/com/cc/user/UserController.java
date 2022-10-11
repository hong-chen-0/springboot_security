package com.cc.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public Object test(HttpServletRequest request) {
    	Object login = request.getSession().getAttribute("users")+",欢迎回来!";
    	return login;
	}
	
    //用户登录(注册session)
	@RequestMapping("/login")
	public String userlogin(String name,HttpServletRequest request, HttpServletResponse response) {
		//用户名存入该用户的session中
        request.getSession().setAttribute("users",name);
    	System.out.println("登录成功");
        return "已存储session";
	}
	
	@RequestMapping("/findByUsername")
	public List<User> findByUsername(String username,HttpServletRequest request) throws Exception {
		//定义页面所需权限
		userService.setRole("12",request);
		return userService.findByUsername(username);
	}
	
	//测试自定义注释模板
    @EagleEye(desc = "测试接口")
    @RequestMapping(value = "/sayHello")
    public String test(String params)throws Exception{
        System.out.println("参数:" + params);
        return "hello "+ params;
    }
    
    //测试自定义注释：检测访问者MYSQL权限列表是否拥有该接口需求的角色（注释里定义接口需要的角色）
    @SuperRole(setRole = "1")
    @RequestMapping(value = "/superRole")
    public List<User> superRole(String username) {
        System.out.println("接口导入参数:" + username);
        return userService.findByUsername(username);
	}

}
