package com.mercury.SpringBootRestDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRestDemo.bean.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

}
