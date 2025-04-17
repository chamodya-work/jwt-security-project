package com.security.jwt.entity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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