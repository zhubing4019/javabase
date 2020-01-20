package com.zbb.exercise.test;

import org.junit.Test;

import java.util.Random;

public class RandomTest {

    @Test
    public void test() {
        Random r1 = new Random(10);
        Random r2 = new Random(10);

        int i = r1.nextInt(10);
        System.out.println(i);
        int i1 = r2.nextInt(10);
        System.out.println(i1);

    }


}
