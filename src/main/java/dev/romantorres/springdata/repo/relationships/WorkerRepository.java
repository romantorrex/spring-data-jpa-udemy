package dev.romantorres.springdata.repo.relationships;

import dev.romantorres.springdata.entity.relationships.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Integer> {

}
