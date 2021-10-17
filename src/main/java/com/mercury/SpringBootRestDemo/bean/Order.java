package com.mercury.SpringBootRestDemo.bean;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "MSI_Order")
public class Order {

    @Id
    @SequenceGenerator(name = "msi_order_seq_gen", sequenceName = "MSI_ORDER_SEQ", allocationSize = 1)
    @GeneratedValue(generator="msi_order_seq_gen", strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Date purchase_date;
    @Column
    private int user_id;
    //hold the list of orderProduct
    //cascade : 级联 when insert or update or delete will do same operation to the corresponding table
//    @JsonManagedReference
    //mappedBy: mapped back, not create a new Object
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "order")
    private List<OrderProduct> purchases;

    public Order() {
    }

    public Order(Date purchase_date, int user_id, List<OrderProduct> purchases) {
        this.purchase_date = purchase_date;
        this.user_id = user_id;
        this.purchases = purchases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<OrderProduct> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<OrderProduct> purchases) {
        this.purchases = purchases;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", purchase_date=" + purchase_date +
                ", user_id=" + user_id +
                ", purchases=" + purchases +
                '}';
    }
}
