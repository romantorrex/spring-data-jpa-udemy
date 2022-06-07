package dev.romantorres.springdata.entity.hierarchy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_note")
public class CreditNote extends Document {
  @Column(name = "credit_note_number")
  private String creditNoteNumber;
}
