package com.thinking.cloud.demo;

import org.apache.ibatis.annotations.One;

/**
 * @author admin
 * @date 2021/1/3 4:19
 */
public class MainOne {

    public static void main(String[] args) {
        //SpringApplication.run(DevopsAssetsManageApplication.class, args);
        A a = new A();
        B b  = new B();
        b = (B) a;

    }

    static class A{

    }

    static class B extends A{

    }
}
