package com.test;

public class Person {
    private String name;
    private String phNo;
    private String address;

    public Person() {

    }
    public Person(String name, String phNo, String address) {
        this.name = name;
        this.phNo = phNo;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
