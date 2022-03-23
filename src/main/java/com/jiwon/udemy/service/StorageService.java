package com.jiwon.udemy.service;

import com.jiwon.udemy.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StorageService {
    private final Logger logger = Logger.getLogger(StorageService.class.getName());

    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;
    private final String apiUrl;
    private final String uploadUrl;

    public StorageService(@Value("${url.server}") String serverUrl,
                          @Value("${url.upload}") String uploadUrl,
                          VideoRepository videoRepository,
                          ImageRepository imageRepository) {
        this.apiUrl = serverUrl;
        this.uploadUrl = uploadUrl;
        this.imageRepository = imageRepository;
        this.videoRepository = videoRepository;
    }

    public Image store(MultipartFile file, Course course) throws IOException {
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String uuid = UUID.randomUUID().toString();
        String path = uploadUrl + "/images/" + uuid;
        String url = apiUrl + "/images/" + uuid;

        logger.log(Level.INFO, "Saving " + filename + " to " + path);
        logger.log(Level.INFO, "Course ID: " + course.getId());

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
        String path = uploadUrl + "/videos/" + uuid;
        String url = apiUrl + "/videos/" + uuid;

        logger.log(Level.INFO, "Saving " + filename + " to " + path);
        logger.log(Level.INFO, "Course ID: " + lecture.getId());

        // Transfer the file
        File dest = new File(path);
        file.transferTo(dest);

        Video video = new Video(lecture, filename, contentType, url);
        lecture.setVideo(video);
        return videoRepository.save(video);
    }

    public byte[] getImage(String id) throws IOException {
        Path path = Path.of(uploadUrl + "/images/" + id);
        return Files.readAllBytes(path);
    }

    public byte[] getVideo(String id) throws IOException {
        Path path = Path.of(uploadUrl + "/videos/" + id);
        return Files.readAllBytes(path);
    }
}