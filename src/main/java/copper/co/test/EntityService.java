package copper.co.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class EntityService {

    @Autowired
    private ExampleEntityRepository repo;

    public List<ExampleEntity> getAllEntities() {
        return repo.findAll();
    }

    public Optional<ExampleEntity> getEntity(Long id) {
        return repo.findById(id);
    }

    public ExampleEntity saveEntity(ExampleEntity exampleEntity) {
        return repo.save(exampleEntity);
    }

    public ExampleEntity getEntityByFirstName(String firstName) {
        return repo.findByFirstName(firstName);
    }

    public ExampleEntity getByLastName(String lastName) {
        return repo.findByLastName(lastName);
    }

    public ExampleEntity getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public ExampleEntity save(ExampleEntity entity) {
        return repo.save(entity);
    }
}
