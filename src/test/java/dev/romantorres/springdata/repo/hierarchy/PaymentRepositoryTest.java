package dev.romantorres.springdata.repo.hierarchy;

import dev.romantorres.springdata.entity.hierarchy.Check;
import dev.romantorres.springdata.entity.hierarchy.CreditCard;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentRepositoryTest {

  @Autowired
  PaymentRepository repository;

  @Test
  void createPaymentWithCreditCard() {
    CreditCard payment = CreditCard.builder()
        .id(1)
        .amount(BigDecimal.TEN)
        .cardNumber("55555")
        .build();

    repository.save(payment);
  }

  @Test
  void createPaymentWithCheck() {
    Check payment = Check.builder()
        .id(2)
        .amount(BigDecimal.valueOf(1000.00))
        .checkNumber("11111")
        .build();

    repository.save(payment);
  }
}
