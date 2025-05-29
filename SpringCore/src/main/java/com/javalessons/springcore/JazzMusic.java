package com.javalessons.springcore;

public class JazzMusic implements Music{
    void init(){
        System.out.println("JazzMusic init");
    }
    void destroy(){
        System.out.println("JazzMusic destroy");
    }
    @Override
    public String getSong(){
        return "Jazz Music Song";
    }
}