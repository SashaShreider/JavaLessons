package com.javalessons.springcore.music;

import org.springframework.stereotype.Component;
@Component
public class ClassicalMusic implements Music {
    @Override
    public String getSong() {
        return "Classical Music Song";
    }
}
