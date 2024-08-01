package com.resser.StarChat_AI.Controller;


import com.resser.StarChat_AI.Entity.User;
import com.resser.StarChat_AI.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initUserAndROles() {
        userService.initRolesAndUser();
    }

    @PostMapping("/createNewUser")
    private User createNewUser (@RequestBody User user){

        return  userService.createNewUser(user);
    }

    @GetMapping("/forAdmin")
    private String forAdmin (){

        return  "url only Accessible for admin";
    }

    @GetMapping("/forUser")
    private String foruser (){

        return  "url  Accessible for User";
    }

}
