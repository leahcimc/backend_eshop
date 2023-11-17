package com.FSSE2309.backend_eshop.data.product.dto;

import com.FSSE2309.backend_eshop.data.product.domainObj.ProductDetailData;
import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionProductData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class SingleProductResponseDto {
    //Attribute

    private int pid;
    private String name;
    private String description;
    @JsonProperty("image_url")
    private String imageUrl;

    private BigDecimal price;

    private int stock;


    //Constructor

    public SingleProductResponseDto(ProductDetailData data){
        this.pid = data.getPid();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
    }

    public SingleProductResponseDto(TransactionProductData data){
        this.pid = data.getPid();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
    }

    //Getter&Setter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

}

