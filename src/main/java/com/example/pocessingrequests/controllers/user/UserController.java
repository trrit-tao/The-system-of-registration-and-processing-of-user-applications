package com.example.pocessingrequests.controllers.user;

import com.example.pocessingrequests.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService service;

    @GetMapping
    @Secured("ADMIN_ROLE")
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @Secured("ADMIN_ROLE")
    @GetMapping("roles")
    public List<RoleDTO> getAllRoles() {
        return service.getAllRoles();
    }

    @Secured("ADMIN_ROLE")
    @PostMapping("{userId}/operator")
    public UserDTO addRoleOperator(@PathVariable Long userId) {
        return service.addRoleOperator(userId);
    }


}
