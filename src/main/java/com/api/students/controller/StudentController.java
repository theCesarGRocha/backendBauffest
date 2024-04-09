package com.api.students.controller;

import com.api.students.models.Student;
import com.api.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired public StudentService studentService;

    @PostMapping("/saveStudent")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return ResponseEntity.ok("student was save successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete student.");
        }

    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        try{
            boolean isDeleted = false;

            isDeleted = studentService.deleteStudent(id);
            if(isDeleted) {
                return ResponseEntity.ok("student was deleted successfully.");
            }else{
                return ResponseEntity.ok("Is not possible delete student with lectures.");
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete student.");

        }
    }

    @GetMapping("/allStudents")
    public ResponseEntity<List<Student>> getAllStudents(){
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok().body(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id){
        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.ok().body(student);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PostMapping("/student/{idStudent}/lecture/{idLecture}")
    public ResponseEntity<?> assignLectureToStudent(@PathVariable Integer idStudent, @PathVariable Integer idLecture){
        try {

            studentService.assignLectureToStudent(idStudent, idLecture);
            return ResponseEntity.ok("Lecture assigned to student successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign lecture to student.");
        }
    }
}
