package com.security.jwt.service;

import com.security.jwt.dto.LoginRequest;
import com.security.jwt.dto.LoginResponse;
import com.security.jwt.entity.Role;
import com.security.jwt.entity.User;
import com.security.jwt.repo.UserRepo;
import com.security.jwt.util.JwtUtil;
import org.apache.catalina.startup.ExpandWar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findById(username).get();
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("user not found with user name");
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        for (Role role:user.getRole()){
//           authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())) ;
//        } // this is we set authorities in using for each

        // now we implement same thing using lambda function
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });

        return authorities;
    }

    public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception{
         String userName=loginRequest.getUserName();
         String userPassword=loginRequest.getUserPassword();
         authenticate(userName,userPassword);
         UserDetails userDetails=loadUserByUsername(userName);
         String newGeneratedToken=jwtUtil.generateToken(userDetails);

         User user=userRepo.findById(userName).get();
         LoginResponse loginResponse = new LoginResponse(user,newGeneratedToken);
         return loginResponse;

    }

    //this is authenticate method use to check username and password using authentication manager
    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));

        }catch (BadCredentialsException e){
            throw new Exception("INVALID CREDENTIALS",e);
        }
    }
}
