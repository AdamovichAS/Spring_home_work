package com.github.adamovichas.project.service;

import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("constructor service")
public class ServiceConstructorInject implements IService {

    private final IDao dao;
    private final String NEW_NAME = "constructor inject";

    @Autowired
    public ServiceConstructorInject(@Qualifier("second dao") IDao dao) {
        this.dao = dao;
    }

    @Override
    public void updateUserName(User user) {
        user.setName(NEW_NAME);
    }
}
