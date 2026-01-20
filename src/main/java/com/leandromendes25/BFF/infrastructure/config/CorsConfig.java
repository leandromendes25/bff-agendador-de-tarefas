package com.leandromendes25.BFF.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer configCors(CorsRegistry registry){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //addMapping adiciona essas configurações para todos os endpoints
                //** vai estar pegando todos, se caso quissemos que fosse só pra login
                //seria /usuario/login
                registry.addMapping("/**")
                        .allowedOrigins("https://localhost:4200")//qual a origem que vai poder acessar
                        .allowedMethods("GET","POST","DELETE","PATCH")
                        .allowedHeaders("*")
                        .allowCredentials(true)//permitir cookies no authorization
                        .maxAge(360);//tempo q o navegador vai cachear essa app, 360 é o padrão
            }
        };

    }
}
