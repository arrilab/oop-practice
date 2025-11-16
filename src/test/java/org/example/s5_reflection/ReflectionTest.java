package org.example.s5_reflection;

import org.example.s5_reflection.annotaion.Controller;
import org.example.s5_reflection.annotaion.Service;
import org.example.s5_reflection.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        var beans = getTypesAnnotatedWith(List.of(
                Controller.class,
                Service.class
        ));

        for (Class<?> bean : beans) {
            log.info("bean={}", bean);
        }
    }

    @Test
    void showClass() throws ClassNotFoundException {
        // 1
        Class<User> clazz1 = User.class;

        // 2
        var user = new User("serverwizard", "홍길동");
        Class<? extends User> clazz2 = user.getClass();

        // 3
        Class<?> clazz3 = Class.forName("org.example.s5_reflection.model.User");

        log.info("clazz1={}", clazz1);
        log.info("clazz2={}", clazz2);
        log.info("clazz3={}", clazz3);

        assertThat(clazz1 == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz1).isTrue();

        log.info("User all declared fields: [{}]", Arrays.stream(clazz1.getDeclaredFields()).collect(Collectors.toList()));
        log.info("User all declared constructors: [{}]", Arrays.stream(clazz1.getConstructors()).collect(Collectors.toList()));
        log.info("User all declared methods: [{}]", Arrays.stream(clazz1.getDeclaredMethods()).collect(Collectors.toList()));
    }

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        // org.example 패키지 기준으로 스캔
        var reflections = new Reflections("org.example");

        var beans = new HashSet<Class<?>>();

        // 전달받은 애노테이션들을 하나씩 돌면서 클래스 수집
        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        return beans;
    }

}