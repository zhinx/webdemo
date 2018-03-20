package com.netease.zhinx.webdemo.model;


public class User {

    private int id;
    private String userName;
    private String password;
    private String nickName;
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
