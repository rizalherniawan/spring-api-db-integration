package com.learning.spring.api.db.integration.springapidbintegration.service;


import java.util.Optional;

import javax.management.relation.RoleList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqUser;
import com.learning.spring.api.db.integration.springapidbintegration.dto.RolesList;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Roles;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Users;
import com.learning.spring.api.db.integration.springapidbintegration.exception.RequestException;
import com.learning.spring.api.db.integration.springapidbintegration.repository.RolesRepository;
import com.learning.spring.api.db.integration.springapidbintegration.repository.UserRepository;
import com.learning.spring.api.db.integration.springapidbintegration.security.SecurityConfiguration;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository roleRepository;

    @Override
    public void registerUser(ReqUser userField) {
        if(StringUtils.isEmpty(userField.getUsername()) || StringUtils.isEmpty(userField.getPassword())) throw new RequestException("email or username cannot be empty");
        if(userField.getUsername().length() > 10 || userField.getPassword().length() > 8) throw new RequestException("username and password must not be longer than 10 charactes and 8 characters");
        else if(userField.getUsername().contains(" ") || userField.getPassword().contains(" ")) throw new RequestException("username or password cannot contain white space");
        Optional<Users> findUser = this.userRepository.findByUsername(userField.getUsername());
        if(findUser.isPresent()) throw new RequestException("username " + findUser.get().getUsername() + " already registered");
        Users createUser = new Users();
        createUser.setUsername(userField.getUsername());
        createUser.setPassword(SecurityConfiguration.bCryptPasswordEncoder().encode(userField.getPassword()));
        this.userRepository.save(createUser);
        Roles roles = new Roles();
        if(StringUtils.isEmpty(userField.getRoles())) {
            roles.setRoleTypes(RolesList.user);
            roles.setUsers(createUser);
        } else if(userField.getRoles().equals("ADMIN")){
            roles.setRoleTypes(RolesList.admin);
            roles.setUsers(createUser);
        } else {
            throw new RequestException("role is not existed");
        }
        this.roleRepository.save(roles);
    }
}
