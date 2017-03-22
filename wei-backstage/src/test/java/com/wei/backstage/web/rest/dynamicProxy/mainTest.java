package com.wei.backstage.web.rest.dynamicProxy;

/**
 * Created by gongw on 2017/3/17.
 */
public class mainTest {

    public static void main(String[] args){
        UserService myUserService = new MyUserService();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(myUserService);
        UserService userService = (UserService)myInvocationHandler.getProxy();
        System.out.println("----------------before-----------------");
        userService.add();
        System.out.println("----------------after------------------");
    }
}
