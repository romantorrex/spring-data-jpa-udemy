package dev.romantorres.springdata.repo.relationships;


import static org.assertj.core.api.Assertions.assertThat;

import dev.romantorres.springdata.entity.relationships.Salary;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OneToOneTest {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Test
    void unidirectional() {
        Optional<Salary> salary = salaryRepository.findById("333333666666");

        assertThat(salary).hasValueSatisfying( s -> {
            assertThat(s.getWorker().getFirstName()).isEqualTo("Mine");
        });
    }

    @Test
    @Transactional
    void biderectional() {
        var worker = workerRepository.findById(1);

        assertThat(worker).hasValueSatisfying(w -> {
            assertThat(w.getParkingSpace().getId()).isEqualTo(1);
            assertThat(w.getParkingSpace().getLocation()).isEqualTo("Second Floor");
        });

        var parkingSpace = parkingSpaceRepository.findById(1);
        assertThat(parkingSpace).hasValueSatisfying(ps -> {
            assertThat(ps.getWorker().getFirstName()).isEqualTo("Minerva");
        });
    }

}
