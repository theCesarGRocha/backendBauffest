package com.api.students.repository;


import com.api.students.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAll();
    Student getStudentById(Integer id);
    void deleteById(Integer id);

    @Query("SELECT s FROM Student s JOIN FETCH s.lectures WHERE s.id = :studentId")
    Student findStudentWithLectures(@Param("studentId") Integer studentId);

}
