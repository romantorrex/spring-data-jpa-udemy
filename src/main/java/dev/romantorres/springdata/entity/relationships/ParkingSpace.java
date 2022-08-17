package dev.romantorres.springdata.entity.relationships;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/** Represents the child table of a  bi-directional one-to-one relationship with {@code Worker} */
@Entity
@Table(name = "parking_space")
@Data
public class ParkingSpace {

    @Id
    private int id;
    private String location;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;
}
