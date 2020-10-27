package com.example.p_demo.demo;

import com.example.p_demo.pojo.Studer;
import com.example.p_demo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayMath {
    private static final Logger logger = LoggerFactory.getLogger(ArrayMath.class);

    public static void main(String[] args) {
//        Person p = new Person();
//        System.out.println(p.address.city.toLowerCase());

        for (int i = 0; i < 10; i++) {
            logger.warn(i+"");
            logger.error(i+"");
        }

//        User user = new User();
//        Studer studer = new Studer();
    }

    public double totalTax(Income... incomes) {
        double total = 0;
        for (Income income: incomes) {
            total = total + income.getTax();
        }
        return total;
    }
}


class Income {
    protected double income;
    public double getTax() {
        return income * 0.1; // 税率10%
    }
}

class Salary extends Income {
    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

class StateCouncilSpecialAllowance extends Income {
    @Override
    public double getTax() {
        return 0;
    }
}


class Person {
    String[] name = new String[2];
    Address address = new Address();
}

class Address {
    String city;
    String street;
    String zipcode;
}


