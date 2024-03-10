package com.example.pocessingrequests.repository.entity;

import com.example.pocessingrequests.controllers.user.Role;
import com.example.pocessingrequests.controllers.user.RoleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public RoleDTO toModel() {
        return new RoleDTO(id, role);
    }
}
