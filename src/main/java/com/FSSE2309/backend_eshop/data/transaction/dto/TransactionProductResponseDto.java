package com.FSSE2309.backend_eshop.data.transaction.dto;

import com.FSSE2309.backend_eshop.data.product.dto.SingleProductResponseDto;
import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionProductData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class TransactionProductResponseDto {

    //Attribute
    private int tpid;
    private SingleProductResponseDto product;
    private int quantity;
    @JsonProperty("subtotal")
    private BigDecimal subTotal;

    //Constructor
    public TransactionProductResponseDto(TransactionProductData data){
        this.tpid = data.getTpid();
        this.product = new SingleProductResponseDto(data);
        this.quantity = data.getQuantity();
        calculateSubTotal(data);
    }

    //Set attribute
    private void calculateSubTotal(TransactionProductData data){
        this.subTotal = data.getPrice().multiply(BigDecimal.valueOf(data.getQuantity()));
    }

    //Getter&Setter

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public SingleProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(SingleProductResponseDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
