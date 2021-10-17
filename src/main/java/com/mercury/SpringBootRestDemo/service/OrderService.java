package com.mercury.SpringBootRestDemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.SpringBootRestDemo.bean.Order;
import com.mercury.SpringBootRestDemo.bean.OrderProduct;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.dao.OrderDao;
import com.mercury.SpringBootRestDemo.dao.OrderProductDao;
import com.mercury.SpringBootRestDemo.dao.ProductDao;
import com.mercury.SpringBootRestDemo.http.Response;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderProductDao orderProductDao;

    public List<Order> getAll(){
        return orderDao.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderDao.findById(id).get();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Response save(Order order) {
        // Spring transaction will automatically roll back if exception out.
        try {
            List<OrderProduct> purchases = order.getPurchases();
            purchases.forEach((orderProduct) -> {
                // enrich the product object
                //because we pass in is simple only id suches
                Product product =  productDao.findById(orderProduct.getProduct().getId()).get();
                orderProduct.setProduct(product);
                //order at first is null, then it will point to order object
                orderProduct.setOrder(order);
            });

            //after save we get id
            //because cascade we save order first then go to order product
            //it will need order id because we already have save order(has key) then it will get order
            //orderHistoryDao.save(order);
            orderDao.save(order);

            return new Response(true);
        } catch (Exception e) {
            return new Response(false);
        }
    }

    //if we put detached session send back, it is merged (update)
    //if we transient session to save then it is insert
    //if is insert it may update the sequence number therefore it will influence the Id

    //order in here is transient session
    public Response edit(Order order) {
        try {
            //in here is detached session it can merge back
            //find Order o from database
            Order o = (Order) orderDao.findById(order.getId()).get();
            //get old purchases which we need to update
            List<OrderProduct> purchasesToRemove = o.getPurchases();

            //get new purchases
            List<OrderProduct> purchases = order.getPurchases();
            // handled update and add products in order
            purchases.forEach((orderProduct) -> {
                Product product = productDao.findById(orderProduct.getProduct().getId()).get();
                orderProduct.setProduct(product);
                orderProduct.setOrder(o);
            });
            // handle remove products in order
            if(purchases.size() > 0) {
                //get all purchases that need to remove
                purchasesToRemove = purchasesToRemove.stream().filter((orderProduct) -> {
                    return !purchases.contains(orderProduct);
                }).collect(Collectors.toList());
            }

            o.setPurchases(purchases);

            //this save is update
            orderDao.save(o);

            deleteOrderProducts(purchasesToRemove);

            return new Response(true);
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false);
        }
    }

    public void deleteOrderProducts(List<OrderProduct> purchases) {
        orderProductDao.deleteAll(purchases);
    }
}