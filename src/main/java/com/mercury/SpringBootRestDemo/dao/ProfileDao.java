package com.mercury.SpringBootRestDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRestDemo.bean.Profile;

public interface ProfileDao extends JpaRepository<Profile, Integer> {

}
