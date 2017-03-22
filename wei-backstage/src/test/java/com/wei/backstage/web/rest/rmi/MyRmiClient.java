package com.wei.backstage.web.rest.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by gongw on 2017/3/22.
 */
public class MyRmiClient {
    public static void main(String[] args){
        try {
            MyHelloRmi myHelloRmi = (MyHelloRmi)Naming.lookup("rmi://localhost:8888/MyHelloRmi");
            UserInfo userInfo = new UserInfo("rmi", 10);
            String userInformation = myHelloRmi.printUserInfo(userInfo);
            System.out.println(userInformation);
            System.out.println(myHelloRmi.sayHello());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
