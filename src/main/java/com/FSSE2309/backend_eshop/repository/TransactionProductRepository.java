package com.FSSE2309.backend_eshop.repository;

import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
    List<TransactionProductEntity> findAllByTransaction_Tid(int tid);
    void deleteAllByTransaction_Tid(int tid);
}
