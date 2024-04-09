package com.api.students.controller;

import com.api.students.models.Lecture;
import com.api.students.service.LectureService;
import com.api.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LectureController {
    @Autowired
    LectureService lectureService;
    @Autowired
    public StudentService studentService;

    @PostMapping("/lecture")
    public ResponseEntity<?> saveLecture(@RequestBody Lecture lecture) {
        try {
            lectureService.saveLecture(lecture);
            return ResponseEntity.ok("Lecture was save successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Save Lecture.");
        }

    }

    @DeleteMapping("/lecture/{id}")
    public ResponseEntity<?> deleteLecture(@PathVariable Integer id){
        try {
            Lecture l = lectureService.getLectureById(id);
            if(l != null){
                lectureService.deleteLecture(l);
            }
            return ResponseEntity.ok("Lecture was deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Delete Lecture.");
        }

    }

    @GetMapping("/allLectures")
    public ResponseEntity<List<Lecture>> getAllLectures(){
        try {
            List<Lecture> lecture = lectureService.getAllLectures();
            return ResponseEntity.ok().body(lecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    @GetMapping("/allUnassignedLecturesByStudentId/{id}")
    public ResponseEntity<List<Lecture>> getAllUnassignedLectures(@PathVariable Integer id){
        try {
            List<Lecture> lecture = lectureService.getAllUnassignedLecturesByStudentId(id);
            return ResponseEntity.ok().body(lecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PostMapping("/student/{idStudent}/lectures")
    public ResponseEntity<?> assignLectureToStudent(@PathVariable Integer idStudent, @RequestBody List<Integer> lectures){
        try {
            for (Integer idLecture: lectures) {
                studentService.assignLectureToStudent(idStudent, idLecture);
            }

            return ResponseEntity.ok("Lecture(s) assigned to student successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign lecture(s) to student.");
        }
    }

    @GetMapping("/lecture/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable Integer id){
        try {
            Lecture lecture = lectureService.getLectureById(id);
            return ResponseEntity.ok().body(lecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
