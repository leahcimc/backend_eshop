package com.FSSE2309.backend_eshop.data.cart.dto;

import com.FSSE2309.backend_eshop.data.cart.domainObj.CartItemData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CartItemResponseDto {
    //Attribute
    private int pid;

    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    private BigDecimal price;

    @JsonProperty("cart_quantity")
    private int quantity;

    private int stock;


    //Constructor
    public CartItemResponseDto(CartItemData data){
        this.pid = data.getPid();
        this.name = data.getName();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.quantity = data.getQuantity();
        this.stock = data.getStock();
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
