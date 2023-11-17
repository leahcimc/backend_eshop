package com.FSSE2309.backend_eshop.repository;

import com.FSSE2309.backend_eshop.data.cart.entity.CartEntity;
import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {
    Optional<CartEntity> findByProductAndUser(ProductEntity product, UserEntity user);
    List<CartEntity> findAllByUser(UserEntity user);
}
