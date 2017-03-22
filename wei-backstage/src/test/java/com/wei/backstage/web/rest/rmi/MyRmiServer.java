package com.wei.backstage.web.rest.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by gongw on 2017/3/22.
 */
public class MyRmiServer {

    public static void main(String[] args){
        try {
            MyHelloRmi myHelloRmi = new MyHelloImpl();
            LocateRegistry.createRegistry(8888);
            try {
                Naming.bind("rmi://localhost:8888/MyHelloRmi" , myHelloRmi);
                System.out.println(">>>>>>>>INFO:远程myHelloRmi对象绑定成功......");
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
