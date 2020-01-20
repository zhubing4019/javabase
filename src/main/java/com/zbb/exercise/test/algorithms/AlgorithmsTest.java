package com.zbb.exercise.test.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class AlgorithmsTest {

    /**
     * 洗牌算法
     * 卡牌序列可能性：n! 阶乘
     * 在概率空间上，要充满这个n！样本空间才算起码的达标
     * Fisher–Yates shuffle 洗牌的有效性，减少重复随机
     * 取随机数，跟末尾交换；末尾不在参与随机取数，重复以上操作
     *
     */
    @Test
    public void shuffle001() {
        Random random = new Random();
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));
        for (int i = array.length - 1; i >= 0; i--) {
            int idx = random.nextInt(i + 1);
//            System.out.println("交换："+ idx+"="+tailIdx);
            //交换
            int temp = array[idx];
            array[idx] = array[i];
            array[i] = temp;
        }
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testss() {
        System.out.println(54 * 53 * 52 * 51);
        int size = 54;
        long sum = 1;
        for (int i = size; i >= 1; i--) {
            sum = sum * i;
            System.out.println(sum);
        }
        System.out.println(sum);
    }

    /**
     * kmp 算法
     */
    @Test
    public void test(){

        String ss = "1111";
        boolean contains = ss.contains("11");
        System.out.println(contains);
    }





}
