package com.wei.backstage.web.rest.dynamicProxy;

/**
 * Created by gongw on 2017/3/17.
 */
public class MyUserService implements UserService {
    @Override
    public void add() {
        System.out.println("------------add---------------");
    }
}
