package com.FSSE2309.backend_eshop.data.transaction.dto;

import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionBillData;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionStatus;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionBillDto {
    //Attribute
    private int tid;
    @JsonProperty("datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;

    //Constructor
    public TransactionBillDto(TransactionBillData data){
        this.tid = data.getTid();
        this.dateTime = data.getDateTime();
        this.status = data.getStatus();
        this.total = data.getTotal();
    }

    //Getter & Setter

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
