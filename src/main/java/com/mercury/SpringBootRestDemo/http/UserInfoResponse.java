package com.mercury.SpringBootRestDemo.http;

import com.mercury.SpringBootRestDemo.bean.UserInfo;

public class UserInfoResponse extends Response {

    private UserInfo userInfo;

    public UserInfoResponse(boolean success, UserInfo userInfo) {
        super(success);
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
