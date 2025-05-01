package org.mrtxee.playgrnd.student.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.mrtxee.playgrnd.student.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  private final Random random = new Random();
  private static final List<String> names = Arrays.asList("Петя", "Маша", "Олег", "Катя");

  public Collection<Student> getStudents() {
    List<Student> sts = new ArrayList<>();
    sts.add(getStudent());
    sts.add(getStudent());
    return sts;
  }

  private Student getStudent() {
    Student st = Student.builder()
      .age(random.nextInt(17, 30))
      .name(names.get(random.nextInt(names.size() - 1)))
      .build();

    return st;
  }

  public Student getStudentOrThrowException() {
    int exceptionProbabilityFactor = random.nextInt(-5, 5);
    if (exceptionProbabilityFactor < 0) {
      throw new RuntimeException("exceptionProbabilityFactor = " + exceptionProbabilityFactor);
    }
    return getStudent();
  }

}
