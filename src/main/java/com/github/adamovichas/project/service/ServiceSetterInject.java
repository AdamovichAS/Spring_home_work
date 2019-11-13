package com.github.adamovichas.project.service;

import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("setter service")
public class ServiceSetterInject implements IService{

    private IDao dao;
    private final String NEW_NAME = "service setter inject";

    @Override
    public void updateUserName(User user) {
        user.setName(NEW_NAME);
    }

    @Autowired
    @Qualifier("first dao")
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public String toString() {
        return dao.createUser().toString();
    }
}
