package com.javalessons.springcore.config;

import com.javalessons.springcore.MusicPlayer;
import com.javalessons.springcore.music.ClassicalMusic;
import com.javalessons.springcore.music.JazzMusic;
import com.javalessons.springcore.music.Music;
import com.javalessons.springcore.music.RockMusic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;


@Configuration
@ComponentScan("com.javalessons.springcore")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

    @Bean
    public ClassicalMusic classicalMusic() {
        return new ClassicalMusic();
    }

    @Bean
    public JazzMusic jazzMusic() {
        return new JazzMusic();
    }

    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }

    @Bean
    public MusicPlayer musicPlayer(
            List<Music> musicList,
            @Value("${musicPlayer.name}") String name,
            @Value("${musicPlayer.volume}") int volume
    ) {
        return new MusicPlayer(musicList, name, volume);
    }

}
