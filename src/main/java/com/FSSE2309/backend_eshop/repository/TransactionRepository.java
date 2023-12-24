package com.FSSE2309.backend_eshop.repository;

import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findAllByUser_Uid (int uid);
}
