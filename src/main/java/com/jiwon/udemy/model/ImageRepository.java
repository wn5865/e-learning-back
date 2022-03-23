package com.jiwon.udemy.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

public interface ImageRepository extends JpaRepository<Image, Long> {
     Set<Image> findImageByCourseId(@RequestParam("id") Long id);
}
