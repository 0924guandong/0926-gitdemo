package com.example.p_demo.pojo;

public abstract class T1 {

    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.run();
    }
}
interface C1{
    void age();
}

abstract class B1{
    private String name;
    public abstract void run();

    public Integer age(){
        return 0;
    }
}

class A1 extends B1{

    @Override
    public void run() {
        System.out.println("A1");
    }
}
