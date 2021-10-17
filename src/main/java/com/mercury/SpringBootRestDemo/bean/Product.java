package com.mercury.SpringBootRestDemo.bean;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "MSI_Product")
public class Product {
    //id generate by database sequence
    @Id
    //define sequence
    //every time current max id increase by 1
    @SequenceGenerator(name = "msi_product_seq_gen", sequenceName = "MSI_PRODUCT_SEQ", allocationSize = 1)
    //tell id to generate the id using upper generator
    @GeneratedValue(generator="msi_product_seq_gen", strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String brand;
    @Column
    private int price;
    @Column
    private int stock;
    @Column
    private String image;

    public Product() {
    }

    public Product(String name, String brand, int price, int stock, String image) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", image='" + image + '\'' +
                '}';
    }
}
