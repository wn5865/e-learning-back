package com.jiwon.udemy.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "resource")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Resource {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "default (uuid_to_bin(uuid()))")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    private String url;
    @CreationTimestamp
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Resource resource = (Resource) o;
        return id != null && Objects.equals(id, resource.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
