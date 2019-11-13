package com.github.adamovichas.project.service;

import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.model.User;

import java.util.List;

public class ServiceCollectionInject implements IService{

    private final List<IDao>daos;
    private final String NEW_NAME = "list inject";

    public ServiceCollectionInject(List<IDao> daos) {
        this.daos = daos;
    }

    @Override
    public void updateUserName(User user) {
        user.setName(NEW_NAME);
    }

    @Override
    public String toString() {
        StringBuilder daosName = new StringBuilder();
        for (IDao dao : daos) {
            daosName.append(dao.createUser()).append(", ");
        }
        return daosName.toString();
    }
}
