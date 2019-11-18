package com.github.adamovichas.project.service;

import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.model.User;

public class ServiceProperty implements IService{

    private IDao dao;
    private final String NEW_NAME;

    public ServiceProperty(IDao dao, String NEW_NAME) {
        this.dao = dao;
        this.NEW_NAME = NEW_NAME;
    }

    @Override
    public void updateUserName(User user) {
        user.setName(NEW_NAME);
    }

    @Override
    public String toString() {
        return "ServiceProperty - " + NEW_NAME;
    }
}
