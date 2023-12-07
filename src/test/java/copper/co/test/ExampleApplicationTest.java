package copper.co.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExampleApplicationTest {

	@Autowired
	ExampleEntityRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	public void databaseIsPopulated() {
		assertThat(repository.findAll().size()).isEqualTo(30);
	}
}
