package com.learning.spring.api.db.integration.springapidbintegration.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.learning.spring.api.db.integration.springapidbintegration.dto.SessionUser;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Roles;
import com.learning.spring.api.db.integration.springapidbintegration.exception.BadCredentialsException;
import com.learning.spring.api.db.integration.springapidbintegration.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import org.apache.commons.lang3.StringUtils;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

@Service
public class JwtTokenService {

    @Autowired
    private CustomUserDetailService userDetailService;


    public String addAuthorizationToken(String user) {
        String token = JWT.create()
            .withSubject(user)
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityParameter.time))
            .sign(Algorithm.HMAC512(SecurityParameter.secretKey));
        return token;
    }

    public Authentication getAuthenticated(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer ")){
            return null;
        }
        String token = authorization.replace("Bearer ", "");
        boolean checkTokenExpiration = this.isExpired(token);
        String username = this.getUsername(token);
        if(username == null || checkTokenExpiration) {
            return  null;
        }
        UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
        SessionUser user = new SessionUser();
        user.setUsername(userDetails.getUsername());
        user.setToken(token);
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority role: userDetails.getAuthorities()){
            roles.add(role.getAuthority());
        }
        user.setRoles(roles);
        AuthenticationTokenImpl auth = new AuthenticationTokenImpl(userDetails.getUsername(), userDetails.getAuthorities());
        auth.setDetails(user);
        auth.authenticate();
        return auth;
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
