package com.jiwon.udemy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "course")
@Getter
@Setter
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    @Type(type="text")
    private String description;
    private String objectives;
    private String requirement;
    private Integer price = 0;
    private Integer totalVideoDuration = 0;
    private Integer numStudents = 0;
    private Integer numRatings = 0;
    private Float totalRating = 0F;

    @CreationTimestamp
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime lastUpdated;

    @ManyToOne @JoinColumn(name = "instructor_id")
    @JsonBackReference
    private User instructor;

    @ManyToOne @JoinColumn(name = "category_id")
    private CourseCategory category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Chapter> chapters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne @JoinColumn(name = "level_id")
    private Level level;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "image_id")
    private Image image;

    public void setInstructor(User user) {
        instructor = user;
        user.addCourse(this);
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
        chapter.setCourse(this);
    }

    public void addReview(Review review) {
        ++numRatings;
        totalRating += review.getRating();
    }

    public void setImage(Image image) {
        this.image = image;
        image.setCourse(this);
    }
    @ManyToMany
    @JoinTable(
        name = "wishlist",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Course> wishlist = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "enrollment",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Course> enrollments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != null && Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
