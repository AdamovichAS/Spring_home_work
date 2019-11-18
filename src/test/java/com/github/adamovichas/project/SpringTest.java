package com.github.adamovichas.project;


import com.github.adamovichas.project.config.Config;
import com.github.adamovichas.project.dao.DaoFirst;
import com.github.adamovichas.project.dao.DaoSecond;
import com.github.adamovichas.project.dao.IDao;
import com.github.adamovichas.project.model.User;
import com.github.adamovichas.project.processor.PostProcessor;
import com.github.adamovichas.project.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class SpringTest {

    /**
     * создать контекст с 1 бином с помощью xml
     */
    @Test
    void xmlTest() {
        final GenericXmlApplicationContext context = new GenericXmlApplicationContext("beans_config.xml");
        final IDao bean = context.getBean(IDao.class);
        final User user = bean.createUser();
        System.out.println("---Бин через XML---");
        System.out.println(user);
        System.out.println("--------");
    }

    /**
     * создать контекст с 1 бином с помощью java config
     */
    @Test
    void configTest() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final IService service = context.getBean("firstService", IService.class);
        final IDao dao = context.getBean("firstDao",IDao.class);
        System.out.println("---Бины через config---");
        User user = dao.createUser();
        System.out.println(user);
        service.updateUserName(user);
        System.out.println(user);
        System.out.println("------------");
    }

    /**
     * создать контекст с 1 бином с помощью аннотаций
     * сделать инжект бина через сеттер
     * Написать свой бинпостпроцессор который будет замерять время работы метода помеченного аннотацией
     */
    @Test
    void annotationTest(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DaoFirst.class);
        context.register(PostProcessor.class);
        context.register(ServiceSetterInject.class);
        context.refresh();
        final IDao dao = context.getBean(IDao.class);
        final IService service = context.getBean(IService.class);
        final User user = dao.createUser();
        System.out.println("-----Бины через аннотации + сеттер----");
        System.out.println(user);
        service.updateUserName(user);
        System.out.println(user);
        System.out.println("------------");
    }

    /**
     * сделать инжект бина через поле
     * сделать инжект 2 бинов одного интерфейса по имени
     */
    @Test
    void fieldInjectTest(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DaoFirst.class);
        context.register(DaoSecond.class);
        context.register(ServiceSetterInject.class);
        context.register(ServiceFieldInject.class);
        context.refresh();

        final IService service = (IService) context.getBean("field service");
        final IService setter_service = (IService) context.getBean("setter service");
        System.out.println("---инжект бина через поле + инжект 2 бинов одного интерфейса по имени---");
        System.out.println(service);
        System.out.println(setter_service);
        System.out.println("---------------");
    }

    /**
     * сделать инжект бина через конструктор
     *
     */
    @Test
    void constructorInject(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DaoFirst.class);
        context.register(DaoSecond.class);
        context.register(ServiceFieldInject.class);
        context.register(ServiceConstructorInject.class);
        context.refresh();
        final IDao dao = (IDao) context.getBean("second dao");
        final IService service = (IService) context.getBean("constructor service");
        final User user = dao.createUser();
        System.out.println("---инжект бина через конструктор---");
        System.out.println(user);
        service.updateUserName(user);
        System.out.println(user);
        System.out.println("-------------");
    }

    /**
     * сделать инжект списка бинов через конструктор
     */
    @Test
    void serviceCollectionInject(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final IService service = context.getBean("collectionService", IService.class);
        System.out.println("---инжект списка бинов через конструктор----");
        System.out.println(service);
        System.out.println("-----------");
    }

    /**
     * сделать инжект значения из файла проперти
     */
    @Test
    void serviceProperty(){
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final IService propertyService = context.getBean("propertyService", IService.class);
        System.out.println("----инжект бина через проперти----");
        System.out.println(propertyService);
        System.out.println("-------------");
    }
}
