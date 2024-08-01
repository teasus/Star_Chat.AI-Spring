package com.resser.StarChat_AI.Dao;

import com.resser.StarChat_AI.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User,String> {

}
