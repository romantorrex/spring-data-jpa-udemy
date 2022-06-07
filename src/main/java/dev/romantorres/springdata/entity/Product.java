package dev.romantorres.springdata.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
  @Id
  @GeneratedValue
  private int id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private BigDecimal price;
}
