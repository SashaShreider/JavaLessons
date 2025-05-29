package com.javalessons.springcore.music;

import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music {
    @Override
    public String getSong(){
        return "Rock Music Song";
    }
}


