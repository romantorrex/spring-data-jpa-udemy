package dev.romantorres.springdata.entity.relationships;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Salary {
    @Id
    private String ssn;
    private int salaryLevel;
    @OneToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
