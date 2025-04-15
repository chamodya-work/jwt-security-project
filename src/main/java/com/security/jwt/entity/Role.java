package com.security.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@Entity
public class Role {
    @Id
    private String roleName;
    private String roleDescription;
}
