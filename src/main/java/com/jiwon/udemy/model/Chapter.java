package com.jiwon.udemy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chapter")
@Getter
@Setter
public class Chapter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sortOrder;

    private String title;

    private LocalDateTime created;

    @ManyToOne @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapter")
    private List<Lecture> lectures = new ArrayList<>();

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
        lecture.setChapter(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Chapter chapter = (Chapter) o;
        return id != null && Objects.equals(id, chapter.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
