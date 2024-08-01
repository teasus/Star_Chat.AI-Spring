package com.resser.StarChat_AI.Dao;


import com.resser.StarChat_AI.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends CrudRepository<Role,String> {

}
