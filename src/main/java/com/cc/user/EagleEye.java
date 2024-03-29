package com.cc.user;

import java.lang.annotation.*;

/**
 * @description: 自动以注解
 * @author: CC
 * @create: 2022-10-11
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented

public @interface EagleEye {

    String desc() default "";

}