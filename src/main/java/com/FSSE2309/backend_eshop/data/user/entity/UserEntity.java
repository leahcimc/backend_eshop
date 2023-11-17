package com.FSSE2309.backend_eshop.data.user.entity;

import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "firebase_uid", nullable = false, unique = true)
    private String firebaseUid;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    //Constructor

    public UserEntity() {
    }

    public UserEntity(FirebaseUserData data){
        this.firebaseUid = data.getFirebaseUid();
        this.email = data.getEmail();
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
