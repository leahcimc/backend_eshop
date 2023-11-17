package com.FSSE2309.backend_eshop.util;

import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;

public class JwtUtil {
    static Logger logger = LoggerFactory.getLogger(JwtAuthenticationToken.class);
    public static FirebaseUserData getFirebaseUser(JwtAuthenticationToken token){
//        return new FirebaseUserData(token);
        try {
            return new FirebaseUserData(token);
        }catch (NullPointerException e){
            logger.warn("No Token OR Anonymous.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
