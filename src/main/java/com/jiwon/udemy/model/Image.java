package com.jiwon.udemy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "image")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Image {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "default (uuid_to_bin(uuid()))")
    private UUID id;
    @NonNull private String name;
    @NonNull private String type;
    private String url;

    @OneToOne(mappedBy = "image")
    @JsonBackReference
    private Course course;

    @Column(columnDefinition = "mediumblob")
    private byte[] data;
    private Long size;
    @CreationTimestamp
    private LocalDateTime created;

    public Image(Course course, @NonNull String name, @NonNull String type, String url) {
        this.course = course;
        this.name = name;
        this.type = type;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;
        return id != null && Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
