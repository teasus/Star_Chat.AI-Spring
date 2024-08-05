package com.resser.StarChat_AI.Configuration;

import com.resser.StarChat_AI.Dao.UserDAO;
import com.resser.StarChat_AI.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDAO.findById(username);

        if(user.isPresent()){
            var userObj = user.get();
            return new org.springframework.security.core.userdetails.User(
                    userObj.getUserName(),
                  userObj.getPassword(),
                    userObj.getUserRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).collect(Collectors.toList())
            );

        }else {
            throw new UsernameNotFoundException("User Name Not Found");
        }


    }
}
