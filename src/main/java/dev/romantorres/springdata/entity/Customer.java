package dev.romantorres.springdata.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer {
  @Id
  private Integer id;
  @Column
  private String name;
  @Column
  private String email;
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private Set<PhoneNumber> phoneNumbers = new HashSet<>();

  public void addPhoneNumber(PhoneNumber number) {
    number.setCustomer(this);
    phoneNumbers.add(number);
  }
}
