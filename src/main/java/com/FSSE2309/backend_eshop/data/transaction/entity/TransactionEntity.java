package com.FSSE2309.backend_eshop.data.transaction.entity;

import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @ManyToOne
    @JoinColumn(name = "buyer_uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    //@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(nullable = false)
    private BigDecimal total;


    //Constructor
    public TransactionEntity() {
    }

    public TransactionEntity(List<CartEntity> checkOutCart, UserEntity user) {
        this.user = user;

        //get localtime
        setIssueTime();
        //set prepare
        setPrepareState();
        //get total
        getTotal(checkOutCart);
    }

    //Set attribute
    private void setIssueTime(){
        this.dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    private void setPrepareState(){
        this.status = TransactionStatus.PREPARE;
    }

    private void getTotal(List<CartEntity> checkOutCart){
        BigDecimal sum = BigDecimal.ZERO;
        for (CartEntity item:
             checkOutCart) {
            sum = sum.add(
                    item.getProduct().getPrice().multiply(
                            BigDecimal.valueOf( item.getQuantity() )
                    )
            );
        }
        this.total = sum;
    }

    //Getter&Setter

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
