package com.security.jwt.service;

import com.security.jwt.entity.User;
import com.security.jwt.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findById(username).get();
        if (user!=null){
            return new org.springframework.security.core.userdetails.User(
                   user.getUserName(),
                    user.getPassword(),
                    getAuthority(user)
            );
        }else {
            throw new UsernameNotFoundException("user not found with user name")
        }
    }
}
