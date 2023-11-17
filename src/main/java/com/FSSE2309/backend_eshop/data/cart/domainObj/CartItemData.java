package com.FSSE2309.backend_eshop.data.cart.domainObj;

import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;

import java.math.BigDecimal;

public class CartItemData {
    //Attribute
    private int pid;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private int quantity;

    private int stock;


    //Constructor
    public CartItemData(CartEntity cartItem){
        this.pid = cartItem.getProduct().getPid();
        this.name = cartItem.getProduct().getName();
        this.imageUrl = cartItem.getProduct().getImageUrl();
        this.price = cartItem.getProduct().getPrice();
        this.quantity = cartItem.getQuantity();
        this.stock = cartItem.getProduct().getStock();
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
