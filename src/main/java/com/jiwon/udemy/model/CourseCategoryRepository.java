package com.jiwon.udemy.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "courseCategories", path = "course-categories")
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
}
