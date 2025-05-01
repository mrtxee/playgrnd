package org.mrtxee.playgrnd.student.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrtxee.playgrnd.student.dto.Student;
import org.mrtxee.playgrnd.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

  private final StudentService studentService;

  @GetMapping
  ResponseEntity<Collection<Student>> getStudents(){
    Collection<Student> sts = studentService.getStudents();
    return ResponseEntity.ok(sts);
  }

  @GetMapping("/try")
  ResponseEntity<Student> getStudent(){
    try {
      //studentService.getStudentOrThrowException();
      Student st = Student.builder()
        .name("aaa")
        .age(16)
        .build();
      return ResponseEntity.ok(st);
    } catch (Exception e){
      final String message = "Сбой извлечения студента";
      log.info(message, e);
      //throw new RuntimeException(message, e);
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
  }

  @GetMapping("/try2")
  ResponseEntity<String> getStudent2(){
    try {
      studentService.getStudentOrThrowException();
      return ResponseEntity.ok("ok");
    } catch (Exception e){
      final String message = "Сбой извлечения студента";
      log.info(message, e);
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
    }
  }
}
