package com.wei.backstage.web.rest.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by gongw on 2017/3/22.
 */
public class MyHelloImpl extends UnicastRemoteObject implements MyHelloRmi{

    public MyHelloImpl() throws RemoteException{
        super();
    }

    @Override
    public String sayHello() {
        return "hello world rmi。。。。。";
    }

    @Override
    public String printUserInfo(UserInfo userInfo) {
        return userInfo.toString();
    }
}
