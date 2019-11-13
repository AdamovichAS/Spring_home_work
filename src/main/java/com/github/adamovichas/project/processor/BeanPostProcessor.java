package com.github.adamovichas.project.processor;


import com.github.adamovichas.project.annotation.TimeMethod;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

            final Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                if(method.isAnnotationPresent(TimeMethod.class)){
 //                   final Type[] types = method.getGenericParameterTypes();
                    method.setAccessible(true);
                    try {
                        long startTime = System.nanoTime();
                        method.invoke(bean);
                        long stopTime = System.nanoTime();
                        long different = stopTime - startTime;
                        System.out.println("Время выполнения метода " + method.getName() + " = " + different);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        return bean;
    }
}
