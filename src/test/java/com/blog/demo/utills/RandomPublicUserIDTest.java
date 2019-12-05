package com.blog.demo.utills;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by damiass on Oct, 2019
 */


class RandomPublicUserIDTest {

    private SecureRandom secureRandom = new SecureRandom();
    private byte bytes[] = new byte[16];
    private Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();





    @Test
    void generateRandomBytesTest() {
        secureRandom.nextBytes(bytes);
        String token = encoder.encodeToString(bytes);
        Assert.assertEquals(22, token.length());


    }


}














