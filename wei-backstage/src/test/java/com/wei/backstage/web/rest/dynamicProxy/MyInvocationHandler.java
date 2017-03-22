package com.wei.backstage.web.rest.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by gongw on 2017/3/17.
 */
public class MyInvocationHandler implements InvocationHandler{

    private Object target;

    public MyInvocationHandler(Object target){
        super();
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(target, args);
        return "didi";
    }
}
