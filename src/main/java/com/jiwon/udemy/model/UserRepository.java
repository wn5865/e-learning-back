package com.jiwon.udemy.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(@RequestParam String email);
}
