package com.javalessons.springcore.music;

import org.springframework.stereotype.Component;

@Component
public class JazzMusic implements Music {
    @Override
    public String getSong(){
        return "Jazz Music Song";
    }
}