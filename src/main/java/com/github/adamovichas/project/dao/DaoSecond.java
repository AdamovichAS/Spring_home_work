package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.model.User;
import org.springframework.stereotype.Component;

@Component("second dao")
public class DaoSecond implements IDao {


    @Override
    public User createUser() {
        return new User("second dao");
    }
}
