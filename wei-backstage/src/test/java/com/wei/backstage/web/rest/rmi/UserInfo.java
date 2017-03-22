package com.wei.backstage.web.rest.rmi;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gongw on 2017/3/22.
 */
@Data
public class UserInfo implements Serializable{

    /**
     * rmi实际上还是网络连接传送的对象需要实现序列化
     */
//    private static final long serialVersionUID = 271947229644133464L;

    private String userName;

    private int age;

    public UserInfo(String userName, int age){
        this.userName = userName;
        this.age = age;
    }

}
