package dev.romantorres.springdata.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "phone_number")
@Data()
@EqualsAndHashCode(exclude = "customer")
public class PhoneNumber {

  @Id
  private Integer id;
  private String number;
  private String type;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
