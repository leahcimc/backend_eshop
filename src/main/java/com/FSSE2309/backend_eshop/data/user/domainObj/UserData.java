package com.FSSE2309.backend_eshop.data.user.domainObj;


import com.FSSE2309.backend_eshop.data.user.entity.UserEntity;

public class UserData {
    //Attribute
    private int uid;
    private String firebaseUid;
    private String email;

    //Constructor
    public UserData(UserEntity entity){
        this.uid = entity.getUid();
        this.firebaseUid = entity.getFirebaseUid();
        this.email = entity.getEmail();
    }

    //Getter&Setter

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
