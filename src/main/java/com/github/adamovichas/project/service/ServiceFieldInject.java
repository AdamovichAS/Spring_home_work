package com.github.adamovichas.project.service;

import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("field service")
public class ServiceFieldInject implements IService {

    @Autowired
    @Qualifier("second dao")
    private IDao dao;
    private final String NEW_NAME = "field inject";

    @Override
    public void updateUserName(User user) {
        user.setName(NEW_NAME);
    }
}
