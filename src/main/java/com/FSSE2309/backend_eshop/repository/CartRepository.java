package com.FSSE2309.backend_eshop.repository;

import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import jakarta.websocket.server.PathParam;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {



    //Optional<CartEntity> findByProductAndUser(ProductEntity product, UserEntity user);
    @Query
            (nativeQuery = true,
            value =
                    "SELECT ci.* FROM `cart_item` ci " +
                            "INNER JOIN `product` p ON ci.`pid` = p.`pid` " +
                            "INNER JOIN e_shop_project.`user` u ON ci.`uid` = u.`uid` " +
                            "WHERE ci.`pid`=:productId " +
                            "AND ci.`uid`=:userId")
    public Optional<CartEntity> findByProductIdAndUserId(
            @Param("productId") int productId,
            @Param("userId") int userId);



    //List<CartEntity> findAllByUser(UserEntity user);
    @Query
            (nativeQuery = true,
            value =
                    "SELECT ci.* FROM `cart_item` ci " +
                            "INNER JOIN e_shop_project.`user` u ON ci.`uid` = u.`uid` " +
                            "WHERE ci.`uid`=:userId")
    public List<CartEntity> findAllByUserId(
            @Param("userId") int userId
    );



    //.save (add new)
    @Modifying
    @Transactional
    @Query
            (nativeQuery = true,
            value =
                    "INSERT INTO `cart_item` (`pid`, `quantity`, `uid`) " +
                            "VALUES (:pid, :quant, :uid)")
//                   + "DECLARE @Id INT = SCOPE_IDENTITY() " +
//                    "SELECT * FROM `cart_item` WHERE `cid` = @Id")
    public void saveToCart(
            @Param("pid") int productId,
            @Param("quant") int quantity,
            @Param("uid") int userId);



    //.save (update quantity)
    @Modifying
    @Transactional
    @Query
            (nativeQuery = true,
                    value =
                            "UPDATE `cart_item` SET `quantity` =:quant " +
                                    "WHERE `pid` =:p")
//                                    +
//                                    "DECLARE @Id INT = SCOPE_IDENTITY() " +
//                                    "SELECT * FROM `cart_item` WHERE `cid` = @Id")
    public void updateQuantity(
            @Param("p") int productId,
            @Param("quant") int newQuantity
    );

    //.delete
    @Modifying
    @Transactional
    @Query
            (nativeQuery = true,
                    value =
                            "DELETE FROM `cart_item` WHERE `cid` =:cid")
    public void deleteItem(
            @Param("cid") int cid
    );

    @Modifying
    @Transactional
    @Query
            (nativeQuery = true,
                    value =
                            "DELETE FROM `cart_item` WHERE `uid` =:uid")
    public void deleteAllByUid(
            @Param("uid") int uid
    );
}
