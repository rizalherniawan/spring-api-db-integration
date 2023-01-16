package com.learning.spring.api.db.integration.springapidbintegration.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Roles;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Users;
import com.learning.spring.api.db.integration.springapidbintegration.exception.BadCredentialsException;
import com.learning.spring.api.db.integration.springapidbintegration.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> findUser = this.userRepository.findByUsername(username);
        if(!findUser.isPresent()) throw new BadCredentialsException("username not found");
        Hibernate.initialize(findUser.get().getRoles());
        return new User(findUser.get().getUsername(), findUser.get().getPassword(), this.mappingRoles(findUser.get().getRoles()));
    }
    

    private Collection<GrantedAuthority> mappingRoles(List<Roles> roles){
        List<GrantedAuthority> authority = new ArrayList<>();
        for(Roles role : roles) {
            authority.add(new SimpleGrantedAuthority(role.getRoleTypes()));
        }
        return authority;
    }
}
