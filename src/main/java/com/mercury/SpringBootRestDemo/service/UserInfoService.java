package com.mercury.SpringBootRestDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mercury.SpringBootRestDemo.bean.User;
import com.mercury.SpringBootRestDemo.bean.UserInfo;
import com.mercury.SpringBootRestDemo.dao.UserDao;
import com.mercury.SpringBootRestDemo.dao.UserInfoDao;
import com.mercury.SpringBootRestDemo.http.Response;
import com.mercury.SpringBootRestDemo.http.UserInfoResponse;

@Service
public class UserInfoService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInfoDao userInfoDao;

	//authentication 当前状态
	public Response addUserInfo(UserInfo userInfo, Authentication authentication) {
		User user = userDao.findByUsername(authentication.getName());
		userInfo.setUser(user);
		return new UserInfoResponse(true, userInfoDao.save(userInfo));
	}
	
	public Response updateUserInfo(UserInfo userInfo) {
		UserInfo ud = userInfoDao.findById(userInfo.getId()).get();
		userInfo.setUser(ud.getUser());
		ud = userInfo;
		userInfoDao.save(ud);
		return new Response(true);
	}
}
