package com.resser.StarChat_AI.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CorsConfig {


    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            private static final String GET = "GET";
            private static final String POST = "POST";
            private static final String DELETE = "DELETE";
            private static final String PUT = "PUT";
            @Override
            public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**")
                       .allowedMethods(GET,PUT,POST,DELETE)
                       .allowedHeaders("*")
                       .allowedOrigins("*")
                       .allowedOriginPatterns("*")
                       .allowCredentials(true);
            }
        };
    }


}
