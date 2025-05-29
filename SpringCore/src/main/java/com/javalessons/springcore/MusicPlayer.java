package com.javalessons.springcore;

import com.javalessons.springcore.music.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicPlayer {

//    принцип IoC
    @Autowired
    private List<Music> musicList = new ArrayList<>();

    @Value("${musicPlayer.name}")
    private String name;

    @Value("${musicPlayer.volume}")
    private int volume;

    public void play(){
        System.out.println("PLaying: ");
        musicList.forEach(a -> System.out.println(a.getSong()));
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}
