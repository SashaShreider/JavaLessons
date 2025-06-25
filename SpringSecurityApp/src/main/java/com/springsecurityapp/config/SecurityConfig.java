package com.springsecurityapp.config;

import com.springsecurityapp.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // конфигурируем spring security и авторизацию
        http
                // 1. НАСТРОЙКА ДОСТУПА (AUTHORIZATION)
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()  // Эти URL доступны всем
                .anyRequest().hasAnyRole("USER", "ADMIN")       // Все остальные URL требуют роли USER или ADMIN
                .and()

                // 2. НАСТРОЙКА ФОРМЫ ЛОГИНА (LOGIN)
                .formLogin()
                .loginPage("/auth/login")                      // Кастомная страница логина
                .loginProcessingUrl("/process_login")           // URL для обработки формы логина
                .defaultSuccessUrl("/hello", true)              // Перенаправление после успешного входа
                .failureUrl("/login?error")                     // Перенаправление при ошибке входа
                .and()

                // 3. НАСТРОЙКА ВЫХОДА (LOGOUT)
                .logout()
                .logoutUrl("/logout")                          // URL для выхода
                .logoutSuccessUrl("/login");                   // Перенаправление после выхода
    }

    //настраиваем аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}