package com.jiwon.udemy.dto;

import com.jiwon.udemy.model.*;
import lombok.Data;

/**
 * DTO for course registration
 */
@Data
public class CourseForm {
    Course course;
    User instructor;
    Level level;
    CourseCategory category;
    Chapter[] chapters;
    Lecture[] lectures;
    Integer[] groups;

    public CourseForm(Course course, Lecture[] lectures) {
        this.course = course;
        this.lectures = lectures;
    }
}
