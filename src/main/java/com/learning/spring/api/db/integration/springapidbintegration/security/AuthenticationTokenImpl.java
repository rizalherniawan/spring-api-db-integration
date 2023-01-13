package com.learning.spring.api.db.integration.springapidbintegration.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.learning.spring.api.db.integration.springapidbintegration.dto.SessionUser;

import lombok.Setter;

public class AuthenticationTokenImpl extends AbstractAuthenticationToken {

    @Setter
    private String username;

    @Setter
    private String password;

    public AuthenticationTokenImpl(String principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = principal;
    }

    public void authenticate() {
        // it will compare with created date plus 1 hour.
        //if (getDetails() != null && getDetails() instanceof SessionUser && !((SessionUser) getDetails()).hasExpired()) {
        if (getDetails() != null && getDetails() instanceof SessionUser) {
            setAuthenticated(true);
        } else {
            setAuthenticated(false);
        }
    }

    @Override
    public Object getCredentials() {
        return password != null ? password : "";
    }

    @Override
    public Object getPrincipal() {
        return username != null ? username : "";
    }

}
