package com.zbb.exercise.test.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    @Test
    public void map() {
        HashMap<String, String> map = new HashMap<>();
        String s = map.get("1");
        System.out.println("遍历 entryset");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        map.put("3", "3");

        Set<Map.Entry<String, String>> entries01 = map.entrySet();
        System.out.println(entries01 == entries);

        System.out.println("遍历 keyset");
        Set<String> strings = map.keySet();
        for (String key : strings) {
            System.out.println(key + "=" + map.get(key));
        }

        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println(value);
        }
    }

    /**
     * 锁模式
     */
    @Test
    public void conrrentMap(){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("3", "3");
        map.put("22", "22");
        map.put("8", "8");
        map.get("111");
        System.out.println(Objects.toString(map));
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.get("");
    }

    /**
     * 使用红黑树实现
     *
     *
     */
    @Test
    public void treeMap(){
        TreeMap<String,String> map = new TreeMap<>();
        map.put("3", "3");
        map.put("22", "22");
        map.put("8", "8");
        System.out.println(Objects.toString(map));
    }

    @Test
    public void set(){
        HashSet<String> set = new HashSet<>();
        set.add("3");
        set.add("1");
        set.add("22");
        set.add("10");
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.print(next+", ");
        }
        System.out.println("");
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("3");
        treeSet.add("1");
        treeSet.add("2");
        treeSet.add("22");
        treeSet.add("10");
        System.out.println(Objects.toString(treeSet));
    }


    @AllArgsConstructor
    class Student {

        private String name;
        private int age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return age == student.age &&
                    Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }


}
