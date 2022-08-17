package dev.romantorres.springdata.entity.relationships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Worker {
    @Id
    @Column(name = "worker_id")
    private int workerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(mappedBy = "worker", fetch = FetchType.LAZY)
    private ParkingSpace parkingSpace;
}
