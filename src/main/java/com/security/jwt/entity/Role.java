package com.security.jwt.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    private String roleName;
    private String roleDescription;
//    @ManyToMany(mappedBy = "role")
//    Set<User> userSet;
}