package com.jiwon.udemy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "video")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Video {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "default (uuid_to_bin(uuid()))")
    private UUID id;

    @NonNull private String name;

    @NonNull private String type;

    @NonNull private String url;

    @Column(columnDefinition = "longblob")
    private byte[] data;

    private Long size;

    private Duration duration;

    @CreationTimestamp
    private LocalDateTime created;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    @JsonBackReference
    private Lecture lecture;

    public Video(
        Lecture lecture,
        @NonNull String name,
        @NonNull String type,
        String url
    ) {
        this.lecture = lecture;
        this.name = name;
        this.type = type;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Video video = (Video) o;
        return id != null && Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
