package com.FSSE2309.backend_eshop.data.user.domainObj;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class FirebaseUserData {
    //Attribute

    private String firebaseUid;
    private String email;

    //Constructor
    public FirebaseUserData(JwtAuthenticationToken token){
        this.firebaseUid = (String) token.getTokenAttributes().get("user_id");
        this.email = (String) token.getTokenAttributes().get("email");
    }

    //Getter&Setter

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
