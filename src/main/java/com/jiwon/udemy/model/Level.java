package com.jiwon.udemy.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "level")
@Getter
@Setter
@RequiredArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String levelName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Level level = (Level) o;
        return id != null && Objects.equals(id, level.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
