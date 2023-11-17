package com.FSSE2309.backend_eshop.data.transaction.entity;

import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_product")
public class TransactionProductEntity {
    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tpid;

    @ManyToOne
    @JoinColumn(name = "tid", nullable = false)
    private TransactionEntity transaction;

    @Column(nullable = false)
    private int pid;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int stock;
    @Column(nullable = false)
    private int quantity;

    //Constructor

    public TransactionProductEntity() {
    }

    public TransactionProductEntity(CartEntity item, TransactionEntity transaction){
        this.transaction = transaction;
        this.pid = item.getProduct().getPid();
        this.name = item.getProduct().getName();
        this.description = item.getProduct().getDescription();
        this.imageUrl = item.getProduct().getImageUrl();
        this.price = item.getProduct().getPrice();
        this.stock = item.getProduct().getStock();
        this.quantity = item.getQuantity();
    }

    //Getter&Setter

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
