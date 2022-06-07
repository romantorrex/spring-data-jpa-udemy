package dev.romantorres.springdata.entity.hierarchy;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ch")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Check extends Payment {
  @Column(name = "check_number")
  private String checkNumber;

  @Builder
  public Check(Integer id, BigDecimal amount, String checkNumber) {
    super(id, amount);
    this.checkNumber = checkNumber;
  }
}
