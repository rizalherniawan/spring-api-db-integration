package com.learning.spring.api.db.integration.springapidbintegration.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import org.apache.commons.lang3.StringUtils;

@Service
public class JwtTokenService {
    public String addAuthorizationToken(String user) {
        String token = JWT.create()
            .withSubject(user)
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityParameter.time))
            .sign(Algorithm.HMAC512(SecurityParameter.secretKey));
        return token;
    }

    public boolean validatingToken(String token) {
        if(this.isExpired(token) || StringUtils.isEmpty(this.getUsername(token))){
            return false;
        }
        return true;
    }

    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean isExpired(String token) {
        if(new Date(System.currentTimeMillis()).after(JWT.decode(token).getExpiresAt())) {
            return true;
        }
        return false;
    }

}
