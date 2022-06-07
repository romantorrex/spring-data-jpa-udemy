package dev.romantorres.springdata.repo;

import static org.assertj.core.api.Assertions.assertThat;

import dev.romantorres.springdata.entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductRepositoryTest {

  @Autowired
  private ProductRepository repository;

  @Test
  void findByPriceGreaterThan() {
    assertThat(repository.findAll()).isNotEmpty();

    List<Product> result = repository.findByPriceGreaterThan(
        new BigDecimal("10000.00"));

    assertThat(result).hasSize(2);
  }

  @Test
  void findByDescriptionIsNull() {
    var result = repository.findByDescriptionIsNull();

    assertThat(result).hasSize(1);
  }

  @Test
  void findAllByPage() {
    Page<Product> pages = repository.findAll(PageRequest.of(1, 5));

    assertThat(pages.getTotalElements()).isEqualTo(10);
    assertThat(pages.getTotalPages()).isEqualTo(2);
    assertThat(pages.stream().count()).isEqualTo(5);
  }

  @Test
  void findAllSorted() {
    Iterable<Product> result = repository.findAll(
        Sort.by(Direction.DESC, "price", "name"));// If Direction is omitted then by default it is ASC.
    var products = new ArrayList<Product>();
    result.forEach(products::add);

    assertThat(products.get(0).getName()).isEqualToIgnoringCase("MacBook Pro");
    assertThat(products.get(products.size() - 1).getName()).isEqualToIgnoringCase("Keychron");
  }

  @Test
  @Transactional // Required to enable first level cache.
  void testFirstLevelCache() {
    var productOne = repository.findById(1).get();
    var productTwo = repository.findById(1).get();

    assertThat(productOne).isSameAs(productTwo);
  }
}
