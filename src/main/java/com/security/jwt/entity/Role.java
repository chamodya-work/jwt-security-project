package com.security.jwt.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
@Entity
@Data
public class Role {
    @Id
    private String roleName;
    private String roleDescription;
}