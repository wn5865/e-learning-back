package com.jiwon.udemy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lecture")
@Getter
@Setter
public class Lecture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sortOrder;

    private String title;

    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne @JoinColumn(name = "chapter_id")
    @JsonBackReference
    private Chapter chapter;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lecture")
    private Video video;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private List<Resource> resources;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private List<Question> questions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "completion",
        joinColumns = @JoinColumn(name = "lecture_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    ) private List<User> completions;

    public void setVideo(Video video) {
        this.video = video;
        video.setLecture(this);
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.setLecture(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Lecture lecture = (Lecture) o;
        return id != null && Objects.equals(id, lecture.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
