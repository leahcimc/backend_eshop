package com.FSSE2309.backend_eshop.data.transaction.domainObj;

import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionEntity;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionProductEntity;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionStatus;
import com.FSSE2309.backend_eshop.data.user.domainObj.UserData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailData {
    //Attribute
    private int tid;
    private UserData userData;

    private LocalDateTime dateTime;

    private TransactionStatus status;

    private BigDecimal total;

    private List<TransactionProductData> items = new ArrayList<>();


    //Constructor
    public TransactionDetailData(TransactionEntity entity, List<TransactionProductEntity> list){
        this.tid = entity.getTid();
        this.userData = new UserData(entity.getUser());
        this.dateTime = entity.getDateTime();
        this.status = entity.getStatus();
        this.total = entity.getTotal();
        addItems(list);
    }

    //Set items
    private void addItems(List<TransactionProductEntity> list){
        for (TransactionProductEntity product:
            list) {
                items.add(new TransactionProductData(product));
        }
    }

    //Getter&Setter

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
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

    public List<TransactionProductData> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductData> items) {
        this.items = items;
    }
}
