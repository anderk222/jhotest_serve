package com.anderk222.jhotest.util;

import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyGenerator {
    
    public static SecretKey jwtKey(int size, String secretPassword) throws Exception {

        byte[] salt = new byte[100];

        PBEKeySpec pbeKeySpec = new PBEKeySpec(secretPassword.toCharArray(), salt, size);

        SecretKey pSecretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pbeKeySpec);

        return new SecretKeySpec(pSecretKey.getEncoded(), "HmacSHA256" );

    }

    public static SecretKey jwtRandomKey(int size,String secretPassword) throws Exception{

        byte[] salt = new byte[100];

        SecureRandom secureRandom = new SecureRandom();

        secureRandom.nextBytes(salt);

        PBEKeySpec pbeKeySpec = new PBEKeySpec(secretPassword.toCharArray(), salt, 1000, size);

        SecretKey pSecretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pbeKeySpec);        

        return new SecretKeySpec(pSecretKey.getEncoded(),"HmacSHA256");

    }

}
