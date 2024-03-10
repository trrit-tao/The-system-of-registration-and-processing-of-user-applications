package com.example.pocessingrequests.service;

import com.example.pocessingrequests.controllers.user.Role;
import com.example.pocessingrequests.controllers.user.RoleDTO;
import com.example.pocessingrequests.controllers.user.UserDTO;
import com.example.pocessingrequests.repository.RoleRepository;
import com.example.pocessingrequests.repository.UserRepository;
import com.example.pocessingrequests.repository.entity.RoleEntity;
import com.example.pocessingrequests.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toModel)
                .collect(Collectors.toList());
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleEntity::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO addRoleOperator(Long userId) {
        RoleEntity roleEntity = roleRepository.findByRole(Role.OPERATOR_ROLE)
                .orElseThrow(() -> new IllegalArgumentException(format("Role %s not found", Role.OPERATOR_ROLE)));
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(format("User %s not found", userId)));

        userEntity.getRole().add(roleEntity);
        return userEntity.toModel();
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public UserEntity getByUsername(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }
}
