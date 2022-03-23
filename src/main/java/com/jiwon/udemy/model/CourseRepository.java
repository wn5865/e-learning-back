package com.jiwon.udemy.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
    Page<Course> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);
}
