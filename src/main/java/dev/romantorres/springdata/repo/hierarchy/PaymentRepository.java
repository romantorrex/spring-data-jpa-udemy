package dev.romantorres.springdata.repo.hierarchy;

import dev.romantorres.springdata.entity.hierarchy.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {}
