package com.example.persistence.repository;


import com.example.persistence.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {


    Lecture getLectureById (Integer id);
}
