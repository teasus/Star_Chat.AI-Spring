package com.resser.StarChat_AI.Controller;


import com.mysql.cj.exceptions.PasswordExpiredException;
import com.resser.StarChat_AI.Configuration.UserDetailService;
import com.resser.StarChat_AI.Dao.UserDAO;
import com.resser.StarChat_AI.Entity.JwtRequest;
import com.resser.StarChat_AI.Entity.JwtResponse;
import com.resser.StarChat_AI.Entity.User;
import com.resser.StarChat_AI.Service.JwtService;
import com.resser.StarChat_AI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class JwtController {


    @Autowired
    private JwtService jwtService;


    @Autowired
    private UserDAO userDAO ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String loginAndGenerateJwt(@RequestBody  JwtRequest user){

        Authentication auth=  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( user.getUserName(),user.getUserPassword()));

        System.out.println("auth "+ auth.isAuthenticated() + " "+  userDetailService.loadUserByUsername(user.getUserName()).getUsername());

        if(auth.isAuthenticated()){

            String token = jwtService.generateToken(userDetailService.loadUserByUsername(user.getUserName()).getUsername());

            return token;







        }else {
            throw new UsernameNotFoundException("Invalid Creds or no user available");
        }




    }




}
