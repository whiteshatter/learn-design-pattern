package com.mint.learn.designpattern;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CommonTest extends AbstractQueuedSynchronizer {
    @Test
    public void testEmptyList() {

        List<Object> emptyList = Collections.emptyList();
        emptyList.add(new Object());
    }


    public void testList() {
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        addList(arrayList, "1");
        addList(linkedList, "1");
    }

    public void addList(List<String> list, String item) {
        list.add(item);
    }

    public void testSpring() {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring-config.xml");
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(CommonTest.class);
        System.out.println(context1.getBean("hello"));
        System.out.println(context2.getBean("hello"));
    }

}
