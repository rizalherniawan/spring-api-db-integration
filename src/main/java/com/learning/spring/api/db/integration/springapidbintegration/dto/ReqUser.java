package com.learning.spring.api.db.integration.springapidbintegration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUser {
    String username;
    String password;
    String roles;
}
