package com.javalessons;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {

//    принцип IoC
    private List<Music> musicList = new ArrayList<>();
    private String name;
    private int volume;

    public MusicPlayer() {}

    public MusicPlayer(List<Music> musicList) {
        this.musicList = musicList;
    }

    public void setMusic(List<Music> musicList) {
        this.musicList = musicList;
    }

    public void play(){
        System.out.println("PLaying: ");
        musicList.forEach(a -> System.out.println(a.getSong()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
