package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.annotation.Profiling;
import com.github.adamovichas.project.model.User;
import org.springframework.stereotype.Component;

@Component("first dao")
public class DaoFirst implements IDao {


    @Override
    @Profiling
    public User createUser() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User("first dao");
    }
}
