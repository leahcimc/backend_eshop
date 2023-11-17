package com.FSSE2309.backend_eshop.data.transaction.domainObj;

import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class TransactionProductData {
    //Attribute
    private int tpid;
    private int pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private int stock;
    private int quantity;

    //Constructor
    public TransactionProductData(TransactionProductEntity entity){
        this.tpid = entity.getTpid();
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
        this.quantity = entity.getQuantity();
    }

    //Getter&Setter

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
