package com.FSSE2309.backend_eshop.data.product.domainObj;


import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDetailData implements Serializable {
    //Attribute

    private int pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private int stock;

    //Constructor
    public ProductDetailData(ProductEntity entity){
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
    }

    //Getter&Setter

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
}
