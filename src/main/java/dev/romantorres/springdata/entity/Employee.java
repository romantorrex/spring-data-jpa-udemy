package dev.romantorres.springdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Employee {
  @Id
  @GenericGenerator(name = "employee_gen", strategy = "dev.romantorres.springdata.generator.EmployeeIdGenerator")
  @GeneratedValue(generator = "employee_gen")
  private String id;
  @Column
  private String firstName;
  @Column
  private String lastName;
}
