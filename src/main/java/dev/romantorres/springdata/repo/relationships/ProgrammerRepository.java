package dev.romantorres.springdata.repo.relationships;

import dev.romantorres.springdata.entity.relationships.Programmer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammerRepository extends CrudRepository<Programmer, Integer> {

}
