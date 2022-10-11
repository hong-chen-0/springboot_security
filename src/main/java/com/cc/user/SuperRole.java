package com.cc.user;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented

public @interface SuperRole {

    String desc() default "";
}
