package com.mercury.SpringBootRestDemo.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "MSI_ORDER_PRODUCT")
public class OrderProduct {
    @Id
    @SequenceGenerator(name = "msi_order_product_seq_gen", sequenceName = "MSI_ORDER_PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(generator="msi_order_product_seq_gen", strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int qty;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Order order;
    //one insert another is update not same operation
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Product product;

    public OrderProduct() {
    }

    public OrderProduct(int id, int qty, Order oder, Product product){
        this.id = id;
        this.qty = qty;
        this.order = order;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", qty=" + qty +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}
