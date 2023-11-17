package com.FSSE2309.backend_eshop.repository;

import com.FSSE2309.backend_eshop.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByPidAndStockGreaterThanEqual(int pid, int quantity);
}
