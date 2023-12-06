package copper.co.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ExampleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExampleEntityRepository repository;

    private ExampleEntity entity;

    @BeforeEach
    public void setUp() {
        entity = repository.save(new ExampleEntity("email@email.com", "john", "joe"));
    }

    @AfterEach
    public void after() {
        repository.delete(entity);
    }


    @Test
    void exampleTest() throws Exception {
       mockMvc.perform(get("/ping"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("pong")));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/users/" + entity.getId())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entity.getId()));
    }

    @Test
    void getByFirstName() throws Exception {
        mockMvc.perform(get("/users/name/first/" + entity.getFirstName())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(entity.getFirstName()));
    }

    @Test
    void getByLastName() throws Exception {
        mockMvc.perform(get("/users/name/last/" + entity.getLastName())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value(entity.getLastName()));
    }

    @Test
    void getByEmail() throws Exception {
        mockMvc.perform(get("/users/email/" + entity.getEmail())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(entity.getEmail()));
    }

    @Test
    void postEntity() throws Exception {
        ExampleEntity requestEntity = new ExampleEntity("mail@email.co.uk", "sarah", "jane");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(requestEntity);
        mockMvc.perform(post("/users").content(json).contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}
