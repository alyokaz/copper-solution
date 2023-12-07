package copper.co.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ExampleRepositoryTest {

    @Autowired
    private ExampleEntityRepository testRepository;

    ExampleEntity entity = new ExampleEntity("email@email.com", "john", "joe");

    @BeforeEach
    public void beforeAll() {
        testRepository.save(entity);
    }


    @Test
    public void canGetByFirstName() {
        ExampleEntity returnedEntity = testRepository.findByFirstName(entity.getFirstName());
        assertThat(entity.getFirstName()).isEqualTo((returnedEntity.getFirstName()));
    }

    @Test
    public void canGetByLastName() {
        ExampleEntity returnedEntity = testRepository.findByLastName(entity.getLastName());
        assertThat(entity.getLastName()).isEqualTo(returnedEntity.getLastName());
    }

    @Test
    public void getGetByEmail() {
        ExampleEntity returnedEntity = testRepository.findByEmail(entity.getEmail());
        assertThat(entity.getEmail()).isEqualTo(returnedEntity.getEmail());
    }


}
