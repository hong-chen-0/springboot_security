package com.cc.user;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//实现自定义注释：检测访问者MYSQL权限列表是否拥有该接口需求的角色
@Aspect
@Component
public class SuperRolePoint {
	
	@Autowired
	private UserMapper userMapper;
	
    // 定义切点
    @Pointcut("@annotation(com.cc.user.SuperRole)")
    public void superRole() {

    }

    // 利用环绕增强来实现我们的功能
    @Around("superRole()&&@annotation(superRole)")
    public Object surroundInform(ProceedingJoinPoint proceedingJoinPoint,SuperRole superRole) throws NoSuchMethodException, SecurityException {

        System.out.println("环绕通知开始...");
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println("请求路径 : " + request.getRequestURL());
        System.out.println("请求方式 : " + request.getMethod());
        System.out.println("接口方法名 : " + proceedingJoinPoint.getSignature().getName());
        System.out.println("CLASS路径 : " + proceedingJoinPoint.getSignature().getDeclaringTypeName());
        System.out.println("URL参数 : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        
        System.out.println("注解参数 : " + superRole.setRole());
		
        try {
        	
    		//SESSION中的用户名
    		Object login = request.getSession().getAttribute("users");
    		if(login != null) {
    			String name = login.toString();
    			//数据库中查询到的角色列表
    			List<String> arr = userMapper.getRole(name);
    			
    			System.out.println("检测得到用户名："+name);
    			System.out.println("角色拥有的权限："+arr);
    			System.out.println("页面需要的权限："+superRole.setRole());
    			
    			//如存在匹配的角色则返回true
    			boolean roleCheck = arr.contains(superRole.setRole());
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
        	
            Object o =  proceedingJoinPoint.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
