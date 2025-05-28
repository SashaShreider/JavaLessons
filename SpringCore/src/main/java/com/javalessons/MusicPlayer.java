package com.javalessons;

public class MusicPlayer {

//    принцип IoC
    private final Music music;
    public MusicPlayer(Music music) {
        this.music = music;
    }
    public void play(){
        System.out.println("PLaying: " +  music.getSong());
    }
}
