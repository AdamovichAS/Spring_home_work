package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.User;
import org.springframework.stereotype.Component;

@Component("first dao")
public class DaoFirst implements IDao {


    @Override
    public User createUser() {
        return new User("first dao");
    }
}