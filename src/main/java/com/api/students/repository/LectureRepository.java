package com.api.students.repository;


import com.api.students.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    Lecture getLectureById (Integer id);

    @Query("SELECT s.lectures FROM Student s WHERE s.id = :studentId")
    Set<Lecture> findLecturesByStudentId(@Param("studentId") Integer studentId);

    @Query("SELECT l FROM Lecture l WHERE NOT EXISTS (SELECT s FROM l.students s WHERE s.id = :studentId)")
    List<Lecture> findUnassignedLecturesByStudentId(@Param("studentId") Integer studentId);

}
