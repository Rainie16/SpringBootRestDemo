package com.mercury.SpringBootRestDemo.dao;

import com.mercury.SpringBootRestDemo.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
