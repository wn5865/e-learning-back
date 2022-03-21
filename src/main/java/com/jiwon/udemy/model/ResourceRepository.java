package com.jiwon.udemy.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Level, Long> {
}
