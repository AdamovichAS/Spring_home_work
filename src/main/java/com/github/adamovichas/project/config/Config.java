package com.github.adamovichas.project.config;

import com.github.adamovichas.project.dao.DaoFirst;
import com.github.adamovichas.project.dao.DaoSecond;
import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.processor.PostProcessor;
import com.github.adamovichas.project.service.IService;
import com.github.adamovichas.project.service.ServiceCollectionInject;
import com.github.adamovichas.project.service.ServiceFirst;
import com.github.adamovichas.project.service.ServiceProperty;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Configuration
@PropertySource("classpath:prop.properties")
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

    @Value("${dao}")
    private String property;

    @Bean("propertyService")
    public IService propertyService(@Qualifier("firstDao") IDao dao){
        return new ServiceProperty(dao,property);
    }
}
