package dev.romantorres.springdata.repo;

import static org.assertj.core.api.Assertions.assertThat;

import dev.romantorres.springdata.entity.Employee;
import dev.romantorres.springdata.repo.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompoundCompoundEmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	void test() {
		var newEmployee = new Employee();
		newEmployee.setFirstName("Roman");
		newEmployee.setLastName("Torres");

		Employee savedEmployee = repository.save(newEmployee);

		assertThat(savedEmployee.getId()).isNotNull();
		System.out.println("CompoundEmployee: " + savedEmployee);
	}
}
