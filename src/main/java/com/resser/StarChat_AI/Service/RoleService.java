package com.resser.StarChat_AI.Service;


import com.resser.StarChat_AI.Dao.RoleDAO;
import com.resser.StarChat_AI.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleDAO roleDao ;
    public Role createNewROle(Role role) {

        return roleDao.save(role);



    }
}
