package com.resser.StarChat_AI.Controller;


import com.resser.StarChat_AI.Entity.SignInForm;
import com.resser.StarChat_AI.Entity.User;
import com.resser.StarChat_AI.Service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initUserAndROles() {
        userService.initRolesAndUser();
    }

    @PostMapping( "/auth/createNewUser")
    private User createNewUser (@RequestBody SignInForm user) throws Exception {

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
