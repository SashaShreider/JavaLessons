package com.javalessons.springcore;

import com.javalessons.springcore.music.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private List<Music> musicList = new ArrayList<>();

    private String name;

    private int volume;

    public MusicPlayer(List<Music> musicList, String name, int volume) {
        this.musicList = musicList;
        this.name = name;
        this.volume = volume;
    }

    public void play() {
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
