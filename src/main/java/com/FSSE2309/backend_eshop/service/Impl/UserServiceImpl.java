package com.FSSE2309.backend_eshop.service.Impl;

import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;
import com.FSSE2309.backend_eshop.repository.UserRepository;
import com.FSSE2309.backend_eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    //Attribute
    private UserRepository userRepository;

    //Constructor
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    //Method from api
    @Override
    public UserEntity getByFirebase(FirebaseUserData data){
        Optional<UserEntity> entity = userRepository.findByFirebaseUid(data.getFirebaseUid());

        if(entity.isEmpty()){
            UserEntity newUser = new UserEntity(data);
            newUser = userRepository.save(newUser);
            return newUser;
        }
        return entity.get();
    }
}
