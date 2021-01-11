package com.xxx.core.annotaions;

import java.lang.annotation.*;

/**
 * Router API Mehtod 标识注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RouteMapping {

    String value() default "";

    /**** 是否覆盖 *****/
    boolean isCover() default true;

    /**** 使用http method *****/
    RouteMethod method() default RouteMethod.GET;

    /**** 接口描述 *****/
    String descript() default "";

    /**
     * 注册顺序，数字越大越先注册
     */
    int order() default 0;

    String mimeType() default "";
}
