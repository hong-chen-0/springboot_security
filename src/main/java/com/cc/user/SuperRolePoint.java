package com.cc.user;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SuperRolePoint {
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
        System.out.println("方法名 : " + proceedingJoinPoint.getSignature().getName());
        System.out.println("类路径 : " + proceedingJoinPoint.getSignature().getDeclaringTypeName());
        System.out.println("参数 : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        
        System.out.println("注解参数 : " + superRole.desc());
        
		//SESSION中的用户名
        HttpSession session =request.getSession(); 
		String username = session.getAttribute("users").toString();
		System.out.println("检测得到用户名："+username);
		
        try {
            // 真实业务代码，这里是伪代码
            Object o =  proceedingJoinPoint.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
