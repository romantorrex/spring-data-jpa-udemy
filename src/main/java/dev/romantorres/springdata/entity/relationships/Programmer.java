package dev.romantorres.springdata.entity.relationships;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Programmer {

  @Id
  private Integer id;
  private String name;
  private BigDecimal salary;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "programmers_projects",
      joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
  private Set<Project> projects;
}
