package com.jiwon.udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
public class UdemyApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(UdemyApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UdemyApplication.class);
    }

    @Bean
    public String uploadPath() {
        return Path.of(System.getProperty("user.dir"), "upload").toString();
    }

    @Bean
    public String imageUploadPath() {
        return Path.of(uploadPath(), "images").toString();
    }

    @Bean
    public String videoUploadPath() {
        return Path.of(uploadPath(), "videos").toString();
    }
}
