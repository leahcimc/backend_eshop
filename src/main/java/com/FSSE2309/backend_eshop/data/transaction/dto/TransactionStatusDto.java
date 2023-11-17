package com.FSSE2309.backend_eshop.data.transaction.dto;

import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionActionStatus;

public class TransactionStatusDto {
    //Attribute
    private String result;

    //Constructor
    public TransactionStatusDto(TransactionActionStatus status){
        this.result = status.name();
    }

    //Getter&Setter

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
