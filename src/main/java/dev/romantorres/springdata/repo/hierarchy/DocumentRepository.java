package dev.romantorres.springdata.repo.hierarchy;

import dev.romantorres.springdata.entity.hierarchy.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer> {

}
