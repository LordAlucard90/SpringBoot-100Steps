package com.lordalucard90.springboottutorials.restfulwebservices.versioning;

public class PersonV1 {
    private String name;

    protected PersonV1(){}

    public PersonV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
