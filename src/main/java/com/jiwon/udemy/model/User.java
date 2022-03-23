package com.jiwon.udemy.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private LocalDateTime created;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setUser(this);
    }

    @ManyToMany
    @JoinTable(
        name = "enrollment",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> enrollments = new HashSet<>();

    public void addEnrollment(Course course) {
        enrollments.add(course);
        int num = course.getNumStudents();
        course.setNumStudents(++num);
    }

    public void removeEnrollement(Course course) {
        if (enrollments.remove(course)) {
            int num = course.getNumStudents();
            course.setNumStudents(--num);
        }
    }

    @ManyToMany
    @JoinTable(
        name = "cart",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> cart = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "wishlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> wishlist = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "completion",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    private Set<Lecture> completions = new HashSet<>();

    public void addCart(Course course) {
        cart.add(course);
    }

    public void addWishlist(Course course) {
        wishlist.add(course);
    }

    public void addCompletions(Lecture lecture) {
        completions.add(lecture);
    }

    public void removeCompletions(Lecture lecture) {
        completions.remove(lecture);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
