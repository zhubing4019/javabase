package com.zbb.exercise.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BigDataTest {

    @Test
    public void cpAndWrite() {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        Date start = new Date();
        for (int i = 0; i < 100000; i++) {
            list.add(new StuInfo[1000]);
        }
        Date end = new Date();
        System.out.println((end.getTime() - start.getTime()));
        System.out.println("================");
        list();
        System.out.println("================");
        synlist();
    }

    public void list() {
        ArrayList list = new ArrayList();
        Date start = new Date();
        for (int i = 0; i < 100000; i++) {
            list.add(new StuInfo[1000]);
        }
        Date end = new Date();
        System.out.println((end.getTime() - start.getTime()));
    }

    public void synlist() {
        List list = Collections.synchronizedList(new ArrayList());
        Date start = new Date();
        for (int i = 0; i < 100000; i++) {
            list.add(new StuInfo[1000]);
        }
        Date end = new Date();
        System.out.println((end.getTime() - start.getTime()));
    }


}
