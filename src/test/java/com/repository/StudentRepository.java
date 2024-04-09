package com.example.persistence.repository;

import com.example.persistence.models.Lecture;
import com.example.persistence.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    List<Student> findAll();
    Student getStudentById(Integer id);


    //@Transactional
    //@Modifying
    @Query("UPDATE Student s SET s.lectures = :lectures WHERE s.id = :studentId")
    void assignLecturesToStudent(@Param("studentId") Integer studentId, @Param("lectures") List<Lecture> lectures);


}
