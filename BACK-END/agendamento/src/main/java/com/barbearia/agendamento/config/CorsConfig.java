package com.barbearia.agendamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") 
                        .allowedOrigins("*") //determina qual o ip sera acessivel ao back
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //tipos de requisiçoes aceitaveis
                        .allowedHeaders("*"); 
            }
        };
    }
}