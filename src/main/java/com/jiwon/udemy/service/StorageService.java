package com.jiwon.udemy.service;

import com.jiwon.udemy.model.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class StorageService {
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;
    private final String apiUrl;
    private final String imageUploadPath;
    private final String videoUploadPath;

    public StorageService(@Qualifier("imageUploadPath") String imageUploadPath,
                          @Qualifier("videoUploadPath") String videoUploadPath,
                          @Qualifier("apiUrl") String apiUrl,
                          VideoRepository videoRepository,
                          ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.videoRepository = videoRepository;
        this.apiUrl = apiUrl;
        this.imageUploadPath = imageUploadPath;
        this.videoUploadPath = videoUploadPath;
    }

    public Image store(MultipartFile file, Course course) throws IOException {
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String uuid = UUID.randomUUID().toString();
        String path = Path.of(imageUploadPath, uuid).toString();
        String url = apiUrl + "/images/" + uuid;

        // Transfer the file
        File dest = new File(path);
        file.transferTo(dest);

        // Save the image to DB
        Image image = new Image(course, filename, contentType, url);
        course.setImage(image);
        return imageRepository.save(image);
    }

    public Video store(MultipartFile file, Lecture lecture) throws IOException {
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String uuid = UUID.randomUUID().toString();
        String path = Path.of(videoUploadPath, uuid).toString();
        String url = apiUrl + "/videos/" + uuid;

        // Transfer the file
        File dest = new File(path);
        file.transferTo(dest);

        Video video = new Video(lecture, filename, contentType, url);
        lecture.setVideo(video);
        return videoRepository.save(video);
    }

    public byte[] getImage(String id) throws IOException {
        Path path = Path.of(imageUploadPath, id);
        return Files.readAllBytes(path);
    }

    public byte[] getVideo(String id) throws IOException {
        Path path = Path.of(videoUploadPath, id);
        return Files.readAllBytes(path);
    }
}
