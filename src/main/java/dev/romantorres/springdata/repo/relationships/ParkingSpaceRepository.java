package dev.romantorres.springdata.repo.relationships;

import dev.romantorres.springdata.entity.relationships.ParkingSpace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends CrudRepository<ParkingSpace, Integer> {

}
