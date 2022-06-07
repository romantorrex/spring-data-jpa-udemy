package dev.romantorres.springdata.entity.hierarchy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Bike extends Vehicle {
  @Column(name = "bike_type")
  private String bikeType;
}
