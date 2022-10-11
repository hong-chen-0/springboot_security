package com.cc.user;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented

//自定义注释：检测访问者MYSQL权限列表是否拥有该接口需求的角色
public @interface SuperRole {
	//定义接口允许的角色
	String setRole() default "guest";
}
