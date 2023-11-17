package com.FSSE2309.backend_eshop.data.cart.dto;

import com.FSSE2309.backend_eshop.data.cart.domainObj.CartActionStatus;

public class CartActionResponseDto {
    //Attribute
    private String result;

    //Constructor
    public CartActionResponseDto(CartActionStatus cartActionStatus){
        this.result = cartActionStatus.name();
    }

    //Getter&Setter

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
