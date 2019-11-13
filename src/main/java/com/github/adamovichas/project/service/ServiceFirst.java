package com.github.adamovichas.project.service;


import com.github.adamovichas.project.annotation.TimeMethod;
import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.model.User;

public class ServiceFirst implements IService {

    private IDao dao;
    private final String NEW_NAME = "first service";

    public ServiceFirst(IDao dao) {
        this.dao = dao;
    }

    public ServiceFirst() {

    }

    @Override
    public void updateUserName(User user) {
        user.setName(NEW_NAME);
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
    @TimeMethod
    public IDao getDao() {
        return dao;
    }
}
