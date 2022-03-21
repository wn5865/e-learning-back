package com.jiwon.udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
public class UdemyApplication {
    public static void main(String[] args) {
        SpringApplication.run(UdemyApplication.class, args);
    }

    @Bean(name = "apiUrl")
    public String getApiUrl() {
        return "http://localhost:8080";
    }

    @Bean(name = "uploadPath")
    public String getUploadPath() {
        return Path.of(System.getProperty("user.dir"), "upload").toString();
    }

    @Bean(name = "imageUploadPath")
    public String getImgUploadPath() {
        return Path.of(getUploadPath(), "images").toString();
    }

    @Bean(name = "videoUploadPath")
    public String getVideoUploadPath() {
        return Path.of(getUploadPath(), "videos").toString();
    }
}