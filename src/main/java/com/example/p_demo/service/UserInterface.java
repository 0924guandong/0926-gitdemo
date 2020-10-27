package com.example.p_demo.service;

public interface UserInterface {

    default String test(){
        return "1";
    }

    int add();
}
