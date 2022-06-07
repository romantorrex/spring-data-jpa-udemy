package dev.romantorres.springdata.repo;

import dev.romantorres.springdata.entity.Student;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Repository with methods that use JPQL **/
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

  @Query("from Student")
  List<Student> findAllStudents();

  @Query("from Student")
  List<Student> findAllStudents(Pageable pageable); // How to make our jpl method pageable

  @Query("select st.firstName from Student st")
  List<Object[]> findAllNames();

  @Query("from Student where score = :score")
  List<Student> findStudentsWithScore(@Param("score") Integer score);

  @Modifying
  @Query("delete from Student where firstName = :firstName")
  void deleteStudentsWithFirstName(@Param("firstName") String firstName);
}
