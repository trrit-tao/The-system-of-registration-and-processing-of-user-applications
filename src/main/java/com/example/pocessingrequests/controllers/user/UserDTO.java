package com.example.pocessingrequests.controllers.user;

import java.util.Set;

public record UserDTO(
        Long id,
        Set<Role> roles,
        String userName
){}
