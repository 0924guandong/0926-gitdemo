package com.example.p_demo.pojo;

public class Person {
    String name;
    private Boolean inFlag;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
    public String toString() {
        return "Person:" + this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInFlag() {
        return inFlag;
    }

    public void setInFlag(Boolean inFlag) {
        this.inFlag = inFlag;
    }
}
