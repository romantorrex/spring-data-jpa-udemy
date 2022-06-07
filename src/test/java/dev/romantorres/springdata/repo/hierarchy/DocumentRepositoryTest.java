package dev.romantorres.springdata.repo.hierarchy;

import dev.romantorres.springdata.entity.hierarchy.Invoice;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentRepositoryTest {

  @Autowired
  private DocumentRepository repository;

  @Test
  void createInvoice() {
    Invoice invoice = new Invoice();

    invoice.setId(1);
    invoice.setInvoiceNumber("IN001");
    invoice.setAmount(BigDecimal.ZERO);

    repository.save(invoice);
  }
}
