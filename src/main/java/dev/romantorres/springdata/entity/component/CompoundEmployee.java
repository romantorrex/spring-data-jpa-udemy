package dev.romantorres.springdata.entity.component;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "compound_employee")
@Data
public class CompoundEmployee {

  @Id
  private Integer id;
  private String name;
  @Embedded
  private Address address;

  @Embeddable
  @Data
  public static class Address {

    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
  }
}
