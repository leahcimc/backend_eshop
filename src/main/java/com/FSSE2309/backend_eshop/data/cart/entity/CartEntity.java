package com.FSSE2309.backend_eshop.data.cart.entity;

import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartEntity {
    //Attribute

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private int quantity;



    //Constructor
    public CartEntity() {
    }

    public CartEntity(ProductEntity product, UserEntity user, int quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    //Getter&Setter


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
