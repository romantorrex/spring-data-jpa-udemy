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
@DiscriminatorValue("cc")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CreditCard extends Payment {

  @Column(name = "card_number")
  private String cardNumber;

  @Builder
  public CreditCard(Integer id, BigDecimal amount, String cardNumber) {
    super(id, amount);
    this.cardNumber = cardNumber;
  }
}
