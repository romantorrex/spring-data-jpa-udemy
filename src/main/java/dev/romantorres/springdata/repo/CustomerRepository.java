package dev.romantorres.springdata.repo;

import dev.romantorres.springdata.entity.Customer;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

  List<Customer> findByEmailAndName(String email, String name);

  @Query("from Customer where email like :partialEmail")
  List<Customer> findByEmailLike(@Param("partialEmail") String partialEmail, Pageable pageable);

  List<Customer> findByIdIn(List<Integer> ids);

  @Modifying
  @Query("update Customer set email = :newEmail where id = :id")
  void updateEmail(@Param("id") Integer id, @Param("newEmail") String newEmail);
}
