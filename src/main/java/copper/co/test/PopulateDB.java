package copper.co.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

public class PopulateDB implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExampleEntityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<Results> resultDTO = restTemplate.getForEntity("https://randomuser.me/api?results=30&nat=gb,us,es,fr", Results.class);
        resultDTO.getBody().results.stream().map(user -> new ExampleEntity(user.getEmail(), user.getName().getFirst(), user.getName().getLast()));
    }

    public static class Results implements Serializable {
        List<User> results;

        public List<User> getResults() {
            return results;
        }

        public void setResults(List<User> results) {
            this.results = results;
        }
    }

    public static class User {
        private String email;

        private Name name;

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class Name {
        private String title;
        private String first;
        private String last;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }
}
