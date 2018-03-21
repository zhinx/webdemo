package com.netease.zhinx.webdemo.model;


public class User {

    /** 用户 ID **/
    private int id;

    /** 用户名 **/
    private String userName;

    /** 用户密码 **/
    private String password;

    /** 用户昵称 **/
    private String nickName;

    /** 用户类型 买家：0 卖家：1**/
    private int userType;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public int getUserType() {
        return userType;
    }

    public void setId(int id) {

        this.id = id;
    }
}
