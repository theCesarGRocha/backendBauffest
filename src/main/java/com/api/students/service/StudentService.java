package com.api.students.service;

import com.api.students.models.Lecture;
import com.api.students.models.Student;
import com.api.students.repository.LectureRepository;
import com.api.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LectureRepository lectureRepository;

    public Student saveStudent(Student s) {
        return studentRepository.save(s);
    }

    public boolean deleteStudent(Integer id) {

        Set<Lecture> lectures = lectureRepository.findLecturesByStudentId(id);

        if(lectures.isEmpty()){
            studentRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.getStudentById(id);
    }

    public void assignLectureToStudent(Integer idStudent, Integer idLecture) {
        Student s = studentRepository.getStudentById(idStudent);
        Lecture l = lectureRepository.getLectureById(idLecture);
        if(l != null) {
            l.getStudents().add(s);
            lectureRepository.save(l);
        }
    }
}
