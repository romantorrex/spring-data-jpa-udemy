package dev.romantorres.springdata.repo;

import dev.romantorres.springdata.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
  List<Product> findByPriceGreaterThan(BigDecimal price);
  List<Product> findByPriceGreaterThan(BigDecimal price, Pageable pageable);
  List<Product> findByDescriptionIsNull();
}
