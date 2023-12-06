package copper.co.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleEntityRepository extends JpaRepository<ExampleEntity, Long> {

    ExampleEntity findByFirstName(String firstName);

    ExampleEntity findByLastName(String lastName);

    ExampleEntity findByEmail(String email);
}
