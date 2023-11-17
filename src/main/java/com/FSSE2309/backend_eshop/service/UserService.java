package com.FSSE2309.backend_eshop.service;

import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;

public interface UserService {
    //Method from api
    UserEntity getByFirebase(FirebaseUserData data);
}
