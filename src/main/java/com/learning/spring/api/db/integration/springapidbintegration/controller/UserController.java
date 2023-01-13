package com.learning.spring.api.db.integration.springapidbintegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.api.db.integration.springapidbintegration.dto.LoginDto;
import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqUser;
import com.learning.spring.api.db.integration.springapidbintegration.dto.Response;
import com.learning.spring.api.db.integration.springapidbintegration.dto.SessionUser;
import com.learning.spring.api.db.integration.springapidbintegration.security.CustomAuthenticationProvider;
import com.learning.spring.api.db.integration.springapidbintegration.security.JwtTokenService;
import com.learning.spring.api.db.integration.springapidbintegration.service.UserServiceImpl;


@RestController
@RequestMapping("/v1/user")
public class UserController {
    
    @Autowired
    UserServiceImpl userService;

    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Autowired
    JwtTokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody ReqUser user) {
        this.userService.registerUser(user);
        return new ResponseEntity<>(new Response<>(200, true, null), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<SessionUser>> login(@RequestBody LoginDto user) {
        Authentication auth = this.authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SessionUser u = (SessionUser) auth.getDetails();
        return new ResponseEntity<>(new Response<>(200, true, u), HttpStatus.OK);
    }
}
