package com.resser.StarChat_AI.Controller;

import com.resser.StarChat_AI.Entity.Role;
import com.resser.StarChat_AI.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {


    @Autowired
    RoleService roleService;

    @PostMapping("/createNewRole")
    public Role createNewRole(@RequestBody Role role) {

        return roleService.createNewROle(role);

    }

}
