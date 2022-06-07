package dev.romantorres.springdata.repo.component;

import dev.romantorres.springdata.entity.component.CompoundEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompoundEmployeeRepository extends CrudRepository<CompoundEmployee, Integer> {}
