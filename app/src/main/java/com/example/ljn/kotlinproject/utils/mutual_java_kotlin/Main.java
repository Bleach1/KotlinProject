package com.example.ljn.kotlinproject.utils.mutual_java_kotlin;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("LJN", 27);
        person.setName("MB");
        person.getName();
        //访问全局函数
        Util.function();
        Util.function2();
    }
}
