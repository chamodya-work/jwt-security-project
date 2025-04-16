package com.security.jwt.service;

import com.security.jwt.entity.Role;
import com.security.jwt.entity.User;
import com.security.jwt.repo.RoleRepo;
import com.security.jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service

public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    public User registerNewUser(User user) {
        return userRepo.save(user);
    }

    public void initRoleAndUser() {
        Role adminRole = new Role();
        Role userRole = new Role();
        if (!roleRepo.existsById("Admin")) {
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin Role");
            roleRepo.save(adminRole);
        }
        if (!roleRepo.existsById("User")) {
            userRole.setRoleName("User");
            userRole.setRoleDescription("User Role");
            roleRepo.save(userRole);
        }
        if (!userRepo.existsById("admin123")) {
            User user = new User();
            user.setUserName("admin123");
            user.setFirstName("Chamodya");
            user.setLastName("Rajapaksha");
            user.setPassword("admin123");
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            user.setRole(roles);
            userRepo.save(user);
        }
        if (!userRepo.existsById("user123")) {
            User user = new User();
            user.setUserName("user123");
            user.setFirstName("Moditha");
            user.setLastName("Rajapaksha");
            user.setPassword("user123");
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRole(roles);
            userRepo.save(user);
        }


    }

}
