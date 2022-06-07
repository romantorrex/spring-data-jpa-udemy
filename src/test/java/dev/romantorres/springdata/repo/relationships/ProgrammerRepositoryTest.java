package dev.romantorres.springdata.repo.relationships;

import static org.assertj.core.api.Assertions.assertThat;

import dev.romantorres.springdata.entity.relationships.Programmer;
import dev.romantorres.springdata.entity.relationships.Project;
import java.math.BigDecimal;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProgrammerRepositoryTest {

  @Autowired
  private ProgrammerRepository repository;

  @Test
  @Transactional
  void createProgrammer() {
    var programmer = new Programmer();
    programmer.setId(1);
    programmer.setName("Roman");
    programmer.setSalary(new BigDecimal(100_000));
    var projects = new HashSet<Project>();
    var project = new Project();
    project.setId(1);
    project.setName("Java AWS");
    projects.add(project);
    programmer.setProjects(projects);
    repository.save(programmer);

    Programmer result = repository.findById(1).get();

    assertThat(result.getName()).isEqualTo("Roman");
    assertThat(result.getProjects()).hasSize(1);
  }
}
