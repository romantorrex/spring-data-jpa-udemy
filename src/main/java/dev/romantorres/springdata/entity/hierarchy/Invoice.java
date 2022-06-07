package dev.romantorres.springdata.entity.hierarchy;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Invoice extends Document {

  @Column(name = "invoice_number")
  private String invoiceNumber;
}
