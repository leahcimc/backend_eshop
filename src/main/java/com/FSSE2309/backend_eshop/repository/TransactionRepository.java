package com.FSSE2309.backend_eshop.repository;

import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
}
