package com.jiwon.udemy.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query("select l from Lecture l where l.chapter in (select c from Chapter c where c.course.id = ?1)")
    Set<Lecture> getLecturesByCourseId(Long id);
}
