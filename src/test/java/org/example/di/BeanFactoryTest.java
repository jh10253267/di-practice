package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        //해당 경로 아래의 클래스 대상으로 리플렉션 적용.
        reflections = new Reflections("org.example");
        Set<Class<?>> preInstantiatedClazz =  getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for(Class<? extends Annotation> annotation: annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);
        assertThat(userController.getUserService()).isNotNull();
    }
}