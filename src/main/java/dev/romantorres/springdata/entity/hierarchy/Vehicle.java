package dev.romantorres.springdata.entity.hierarchy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {
  @Id
  private Integer id;
  private String brand;
}
