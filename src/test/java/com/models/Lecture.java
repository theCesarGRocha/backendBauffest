package com.example.persistence.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        })
    @JoinTable(name = "lecture_student",
        joinColumns = { @JoinColumn(name = "lecture_id") },
        inverseJoinColumns = { @JoinColumn(name = "student_id") })
    private List<Student> students;

}
