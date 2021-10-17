package com.mercury.SpringBootRestDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercury.SpringBootRestDemo.bean.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {


    public Product findByName(String name);

    @Query("select p from Product p where p.price = :price")
    public Product getByPrice(@Param("price") int price);

//    public Product findByPrice(int price);
}
