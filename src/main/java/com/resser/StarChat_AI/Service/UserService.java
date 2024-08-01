package com.resser.StarChat_AI.Service;


import com.resser.StarChat_AI.Dao.RoleDAO;
import com.resser.StarChat_AI.Dao.UserDAO;
import com.resser.StarChat_AI.Entity.Role;
import com.resser.StarChat_AI.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO ;


    @Autowired
    private RoleDAO roleDAO;
    public User createNewUser (User user) {

        return  userDAO.save(user);
    }

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("admin");
        adminRole.setRoleDescription("admin role for the application");

        Role userRole = new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("default role for newly created user");
        roleDAO.save(adminRole);
        roleDAO.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin000");
        adminUser.setFirstName("ahmed");
        adminUser.setLastName("admin");
        adminUser.setPassword("admin000");

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        adminUser.setUserRoles(adminRoles);
        userDAO.save(adminUser);

        User user = new User();
        user.setUserName("user000");
        user.setFirstName("ahmed");
        user.setLastName("rashid");
        user.setPassword("user000");

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        user.setUserRoles(userRoles);
        userDAO.save(user);





    }

}
