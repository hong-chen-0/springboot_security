package com.cc.user;

import java.lang.annotation.*;

/**
 * @description: 自动以注解
 * @author: Mr-box
 * @create: 2019-05-23 10:00
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented

public @interface EagleEye {

    String desc() default "";

}