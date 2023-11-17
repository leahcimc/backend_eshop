package com.FSSE2309.backend_eshop.repository;

import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
