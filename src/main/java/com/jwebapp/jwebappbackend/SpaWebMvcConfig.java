package com.jwebapp.jwebappbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpaWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward all unmatched requests to /index.html
        registry.addViewController("/{spring:[\w-]+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{spring:[\w-]+}/{spring:[\w-]+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{spring:[\w-]+}/{spring:[\w-]+}/{spring:[\w-]+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{spring:[\w-]+}/{spring:[\w-]+}/{spring:[\w-]+}/{spring:[\w-]+}")
                .setViewName("forward:/index.html");
        registry.addViewController("/**/{spring:[\w-]+}")
                .setViewName("forward:/index.html");
    }
}