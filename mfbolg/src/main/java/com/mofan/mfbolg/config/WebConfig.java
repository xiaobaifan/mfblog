package com.mofan.mfbolg.config;

/**
 * @program: mfbolg
 * @description:跨域
 * @author: mofan
 * @create: 2024-11-03 22:19
 **/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3100")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("Content-Type", "Authorization")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}