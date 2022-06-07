package dev.romantorres.springdata.repo;

import static org.assertj.core.api.Assertions.assertThat;

import dev.romantorres.springdata.entity.Customer;
import dev.romantorres.springdata.entity.PhoneNumber;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository repository;

  @Test
  void testFindByIdIn() {
    List<Integer> ids = List.of(1, 2, 3);
    var result = repository.findByIdIn(ids);

    assertThat(result).hasSize(3);
  }

  @Test
  @Transactional
  void testUpdateEmail() {
    repository.updateEmail(1, "new_email@company.com");

    Customer customer = repository.findById(1).get();
    assertThat(customer.getEmail()).isEqualTo("new_email@company.com");
  }


  @Test
  void findByEmailLike() {
    var result = repository.findByEmailLike("%gmail%", PageRequest.of(1, 2));
    assertThat(result).hasSize(2);
    result = repository.findByEmailLike("%gmail%", PageRequest.of(0, 5));
    assertThat(result).hasSize(5);
  }

  @Test
  void findByEmailLikeSorting() {
    var result = repository.findByEmailLike("%gmail%", PageRequest.of(0, 5, Direction.DESC, "name"));

    assertThat(result).isNotEmpty();
    result.forEach(System.out::println);
  }

  @Transactional // Lazy loading requires it to work
  @Test
  void createWithPhones() {
    Customer customer = new Customer();
    customer.setId(0);
    customer.setName("Customer 1");
    var p1 = new PhoneNumber();
    p1.setId(1);
    p1.setNumber("11111");
    p1.setType("casa");
    customer.addPhoneNumber(p1);
    var p2 = new PhoneNumber();
    p2.setId(2);
    p2.setNumber("55555");
    p2.setType("trabajo");
    customer.addPhoneNumber(p2);
    Customer savedCustomer = repository.save(customer);

    assertThat(repository.findById(savedCustomer.getId()).get().getPhoneNumbers()).hasSize(2);
  }
}
