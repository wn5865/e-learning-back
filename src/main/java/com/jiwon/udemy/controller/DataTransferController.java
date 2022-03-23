package com.jiwon.udemy.controller;

import com.jiwon.udemy.dto.CourseForm;
import com.jiwon.udemy.model.*;
import com.jiwon.udemy.service.StorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class DataTransferController {

    private final StorageService storageService;
    private final CourseRepository courseRepository;

    public DataTransferController(CourseRepository courseRepository, StorageService storageService) {
        this.courseRepository = courseRepository;
        this.storageService = storageService;
    }

    @RequestMapping("/")
    public @ResponseBody String welcome() {
        return "Welcome!";
    }

    @PostMapping("/upload/courses")
    public ResponseEntity<?> uploadCourse(@RequestBody CourseForm courseForm) {
        Course course = courseForm.getCourse();
        Chapter[] chapters = courseForm.getChapters();
        Lecture[] lectures = courseForm.getLectures();
        Integer[] groups = courseForm.getGroups();

        course.setInstructor(courseForm.getInstructor());
        course.setCategory(courseForm.getCategory());
        course.setLevel(courseForm.getLevel());
        Arrays.stream(chapters).forEach(course::addChapter);
        addLecturesToChapters(chapters, lectures, groups);

        courseRepository.save(course);
        return ResponseEntity.ok().body(new CourseForm(course, lectures));
    }

    @PostMapping("/upload/images")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,
                                         @RequestParam("course") Course course) throws IOException {
        Image returned = storageService.store(file, course);
        return ResponseEntity.ok().body(returned);
    }

    @PostMapping("/upload/videos")
    public ResponseEntity<?> uploadVideo(@RequestParam("video") MultipartFile file,
                                         @RequestParam("lecture") Lecture lecture) throws IOException {
        Video savedVideo = storageService.store(file, lecture);
        return ResponseEntity.ok().body(savedVideo);
    }

    @GetMapping("images/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) throws IOException {
        byte[] data = storageService.getImage(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + id + "\"")
            .body(data);
    }

    @GetMapping("videos/{id}")
    public ResponseEntity<?> getVideo(@PathVariable String id) throws IOException {
        byte[] data = storageService.getVideo(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + "\"")
            .body(data);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
    }

    private void addLecturesToChapters(Chapter[] chapters, Lecture[] lectures, Integer[] groups) {
        int i = 0, j = 0;
        for (int g : groups) {
            while (g-- > 0)
                chapters[i].addLecture(lectures[j++]);
            ++i;
        }
    }
}
