package com.learning.spring.api.db.integration.springapidbintegration.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Roles;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.learning.spring.api.db.integration.springapidbintegration.dto.SessionUser;
import com.learning.spring.api.db.integration.springapidbintegration.exception.BadCredentialsException;
import com.learning.spring.api.db.integration.springapidbintegration.exception.RequestException;
import com.learning.spring.api.db.integration.springapidbintegration.repository.UserRepository;
import com.learning.spring.api.db.integration.springapidbintegration.service.CustomUserDetailService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";
        if(username == null || "".equals(username)) throw new RequestException("Username or password cannot be null");
        Optional<Users> findUser = this.userRepository.findByUsername(username);
        if(!findUser.isPresent()) throw new BadCredentialsException("username not found");
        if(!bCryptPasswordEncoder.matches(password, findUser.get().getPassword())) throw new BadCredentialsException("Password incorrect");
        SessionUser u = new SessionUser();
        u.setUsername(username);
        u.setToken(this.tokenService.addAuthorizationToken(username));
        List<String> roles = new ArrayList<String>();
        for(Roles role: findUser.get().getRoles()) {
            roles.add(role.getRoleTypes());
        }
        u.setRoles(roles);
        AuthenticationTokenImpl auth = new AuthenticationTokenImpl(password, Collections.emptyList());
        auth.setDetails(u);
        auth.setAuthenticated(true);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
    
}
