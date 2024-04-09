package com.api.students.service;

import com.api.students.models.Lecture;
import com.api.students.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {
    @Autowired private LectureRepository lectureRepository;


    public Lecture saveLecture(Lecture l) {
        return lectureRepository.save(l);
    }



    public void deleteLecture(Lecture l) {
        lectureRepository.delete(l);
    }


    public Lecture getLectureById(Integer id) {
        return lectureRepository.getLectureById(id);
    }

    public List<Lecture> getAllLectures(){
        return (List<Lecture>) lectureRepository.findAll();
    }

    public List<Lecture> getAllUnassignedLecturesByStudentId(Integer id) {
        return lectureRepository.findUnassignedLecturesByStudentId(id);
    }
}
