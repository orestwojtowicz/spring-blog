package com.blog.demo.utills;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by damiass on Oct, 2019
 */

public class RandomPublicUserID {

    private SecureRandom secureRandom = new SecureRandom();
    private byte bytes[] = new byte[16];
    private Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public String generateRandomBytes() {
        secureRandom.nextBytes(bytes);
        String token = encoder.encodeToString(bytes);
        return token;
    }

}
