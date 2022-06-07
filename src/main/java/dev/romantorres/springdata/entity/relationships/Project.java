package dev.romantorres.springdata.entity.relationships;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Project {
  @Id
  private Integer id;
  private String name;
  @ManyToMany(mappedBy = "projects")
  private Set<Programmer> programmers;
}
