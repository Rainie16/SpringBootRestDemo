package com.mercury.SpringBootRestDemo.dao;

import com.mercury.SpringBootRestDemo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
//actually is criteria in hibernate
     User findByUsername(String username);
}
