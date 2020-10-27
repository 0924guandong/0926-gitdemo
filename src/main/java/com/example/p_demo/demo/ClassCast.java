package com.example.p_demo.demo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class ClassCast {
    public static void main(String[] args) {
        Date minTime = new Date(0L);
        System.out.println(minTime.getTime());

        Date now = new Date();
        //一天时间
        long beforDateTime = 1000*60*60*24;
        Date date = new Date(now.getTime() - beforDateTime);
        long one_before = date.getTime();
        System.out.println(one_before);
//
//        Calendar c = Calendar.getInstance();
//        // 清除所有:
//        c.clear();
//        // 设置2019年:
//        c.set(Calendar.YEAR, 2019);
//        // 设置9月:注意8表示9月:
//        c.set(Calendar.MONTH, 8);
//        // 设置2日:
//        c.set(Calendar.DATE, 2);
//        // 设置时间:
//        c.set(Calendar.HOUR_OF_DAY, 21);
//        c.set(Calendar.MINUTE, 22);
//        c.set(Calendar.SECOND, 23);
//        System.out.println(c.getTime());
//        Person2[] ps = new Person2[] {
//                new Person2("Bob", 61),
//                new Person2("Alice", 88),
//                new Person2("Lily", 75),
//        };
//        Arrays.sort(ps);
//        System.out.println(Arrays.toString(ps));
//
//        Number number = new Integer(11);
//
//        Person3<String, Integer, Character> stringIntegerCharacterPerson3 = new Person3<>("a", 2, 'c');
//
//        Person2 a = new Person2("a", 22);
//        Person5<Integer> integerPerson5 = new Person5<>(111);
//        System.out.println(integerPerson5);
    }
}

class Person5<T extends Number>{
    private T age;

    public Person5(T age) {
        this.age = age;
    }
}

class Person3<T,K,G>{
    private T name;
    private K score;
    private G color;


    public Person3(T name, K score, G color) {
        this.name = name;
        this.score = score;
        this.color = color;
    }
}

class Person2 implements Comparable<Person2>{
    private String name;
    private Integer score;

    public Person2(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Person2 o) {
        return o.score.compareTo(this.score);
    }
}


