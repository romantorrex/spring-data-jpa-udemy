package dev.romantorres.springdata.repo.component;

import dev.romantorres.springdata.entity.component.CompoundEmployee;
import dev.romantorres.springdata.entity.component.CompoundEmployee.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompoundEmployeeRepositoryTest {

  @Autowired
  private CompoundEmployeeRepository repository;

  @Test
  void createEmployee() {
    var addr = new Address();
    addr.setStreet("Av. Uno");
    addr.setCity("Puebla");
    addr.setState("Puebla");
    addr.setCountry("Mexico");
    var emp = new CompoundEmployee();
    emp.setId(0);
    emp.setName("Roman Torres");
    emp.setAddress(addr);

    repository.save(emp);
  }
}
