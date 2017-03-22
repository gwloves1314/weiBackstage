package com.wei.backstage.web.rest.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by gongw on 2017/3/22.
 */
public interface MyHelloRmi extends Remote{
    /**
     * 定义自己的远程方法调用接口，需要抛出RemoteException异常
     * @return
     * @throws RemoteException
     */
    String sayHello() throws RemoteException;
    String printUserInfo(UserInfo userInfo) throws RemoteException;
}
