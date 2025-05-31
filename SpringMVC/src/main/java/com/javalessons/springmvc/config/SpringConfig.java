package com.javalessons.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc  // Аналог <mvc:annotation-driven />
@ComponentScan("com.javalessons.springmvc")  // Аналог <context:component-scan />
public class SpringConfig implements WebMvcConfigurer {

    // 1. Настройка TemplateResolver (указывает, где искать HTML-шаблоны)
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");  // Папка с шаблонами
        templateResolver.setSuffix(".html");           // Расширение файлов
        templateResolver.setTemplateMode("HTML");      // Режим шаблонов
        templateResolver.setCharacterEncoding("UTF-8"); // Кодировка
        return templateResolver;
    }

    // 2. Настройка TemplateEngine (обработчик Thymeleaf)
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true); // Поддержка SpEL
        return templateEngine;
    }

    // 3. Настройка Thymeleaf ViewResolver
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }
}