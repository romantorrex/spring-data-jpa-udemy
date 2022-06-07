package dev.romantorres.springdata.repo;

import static org.assertj.core.api.Assertions.assertThat;

import dev.romantorres.springdata.entity.Student;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository repository;

  @Test
  void testFindAllStudents() {
    List<Student> result = repository.findAllStudents();

    assertThat(result).isNotEmpty();
  }

  @Test
  void testFindAllNames() {
    List<Object[]> result = repository.findAllNames();

    assertThat(result).isNotEmpty();
    result.forEach(a -> System.out.println(Arrays.toString(a)));
  }

  @Test
  void testFindStudentsWithScore() {
    List<Student> students = repository.findStudentsWithScore(10);

    assertThat(students).hasSize(1);
    assertThat(students.get(0).getFirstName()).isEqualTo("Albert");
  }
}
