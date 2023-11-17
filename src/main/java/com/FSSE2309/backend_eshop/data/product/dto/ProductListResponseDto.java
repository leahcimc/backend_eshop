package com.FSSE2309.backend_eshop.data.product.dto;

import com.FSSE2309.backend_eshop.data.product.domainObj.ProductDetailData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductListResponseDto {
    //Attribute

    private int pid;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;

    private BigDecimal price;

    @JsonProperty("has_stock")
    private boolean hasStock;

    //Constructor

    public ProductListResponseDto(ProductDetailData data){
        this.pid = data.getPid();
        this.name = data.getName();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.hasStock = data.getStock() != 0;
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

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }
}
