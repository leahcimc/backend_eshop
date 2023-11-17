package com.FSSE2309.backend_eshop.data.transaction.dto;

import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionDetailData;
import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionProductData;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailResponseDto {
    //Attribute
    private int tid;
    @JsonProperty("buyer_uid") //user
    private int buyerUid;
    @JsonProperty("datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private String status;

    private BigDecimal total;
    private List<TransactionProductResponseDto> items = new ArrayList<>();


    //Constructor
    public TransactionDetailResponseDto(TransactionDetailData data){
        this.tid = data.getTid();
        this.buyerUid = data.getUserData().getUid();
        this.dateTime = data.getDateTime();
        this.status = data.getStatus().name();
        this.total = data.getTotal();
        setItemList(data);
    }

    //Set attribute
    private void setItemList(TransactionDetailData data){
        for (TransactionProductData product:
             data.getItems()) {
            items.add(new TransactionProductResponseDto(product));
        }
    }

    //Getter&Setter


    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(int buyerUid) {
        this.buyerUid = buyerUid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseDto> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResponseDto> items) {
        this.items = items;
    }
}
