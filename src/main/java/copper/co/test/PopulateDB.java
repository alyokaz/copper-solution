package copper.co.test;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

@Component
public class PopulateDB implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExampleEntityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<Results> resultDTO = restTemplate.getForEntity("https://randomuser.me/api?results=30&nat=gb,us,es,fr", Results.class);
        resultDTO.getBody().results.stream()
                .map(user -> new ExampleEntity(user.getEmail(), user.getName().getFirst(), user.getName().getLast()))
                .forEach(entity -> repository.save(entity));
    }

    @Data
    public static class Results implements Serializable {
        List<User> results;
    }

    @Data
    public static class User {
        private String email;
        private Name name;
    }

    @Data
    public static class Name {
        private String title;
        private String first;
        private String last;
    }
}
