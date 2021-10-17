package com.mercury.SpringBootRestDemo.dao;

import com.mercury.SpringBootRestDemo.bean.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {
}
