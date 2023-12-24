package com.FSSE2309.backend_eshop.data.transaction.domainObj;

import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionEntity;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionStatus;
import com.FSSE2309.backend_eshop.data.user.domainObj.UserData;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionBillData {
    //Attribute
    private int tid;
    private UserData user;
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;

    //Constructor
    public TransactionBillData(TransactionEntity entity) {
        this.tid = entity.getTid();
        this.user = new UserData(entity.getUser());
        this.dateTime = entity.getDateTime();
        this.status = entity.getStatus();
        this.total = entity.getTotal();
    }


    //Getter & Setter

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
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
