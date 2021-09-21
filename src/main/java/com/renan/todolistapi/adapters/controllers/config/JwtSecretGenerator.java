package com.renan.todolistapi.adapters.controllers.config;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwtSecretGenerator {
    
    private String secret;
    private SecretKey secretKey;
    private static JwtSecretGenerator jwtGenerator;

    public JwtSecretGenerator() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        secret = Encoders.BASE64.encode(secretKey.getEncoded());
    }

    public static JwtSecretGenerator Instance() {

        if (jwtGenerator == null) 
            jwtGenerator = new JwtSecretGenerator();

        return jwtGenerator;
    }

    public String getSecret() {
        return secret;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
