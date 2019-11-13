package com.github.adamovichas.project.config;

import com.github.adamovichas.project.dao.DaoFirst;
import com.github.adamovichas.project.dao.DaoSecond;
import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.service.IService;
import com.github.adamovichas.project.service.ServiceCollectionInject;
import com.github.adamovichas.project.service.ServiceFirst;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class Config {


    @Bean
    public IDao firstDao() {
        return new DaoFirst();
    }

    @Bean
    public IDao secondDao(){
        return new DaoSecond();
    }

    @Bean("firstService")
    public IService firstService(IDao firstDao){
        return new ServiceFirst(firstDao);
    }

    @Bean
    public IService collectionService(){
        List<IDao>daos = new ArrayList<>();
        daos.add(firstDao());
        daos.add(secondDao());
        return new ServiceCollectionInject(daos);
    }
}
