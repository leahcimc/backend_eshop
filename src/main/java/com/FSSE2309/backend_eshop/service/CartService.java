package com.FSSE2309.backend_eshop.service;

import com.FSSE2309.backend_eshop.data.cart.domainObj.CartActionStatus;
import com.FSSE2309.backend_eshop.data.cart.domainObj.CartItemData;
import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;

import java.util.List;

public interface CartService {
    //Method for api
    public CartActionStatus addCartItem(Object pid, Object quantity, FirebaseUserData data);

    List<CartItemData> getCartList(FirebaseUserData userData);

    CartItemData updateCartItem(Object pid, Object quantity, FirebaseUserData data);

    CartActionStatus removeCartItem(Object pid, FirebaseUserData data);

    List<CartEntity> getCartEntityList(UserEntity user);

    void clearUserCart(List<CartEntity> checkOutCart);
}
