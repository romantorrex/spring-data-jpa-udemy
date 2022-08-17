package dev.romantorres.springdata.repo.relationships;

import dev.romantorres.springdata.entity.relationships.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, String> {

}
