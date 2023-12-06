package copper.co.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "example_entity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleEntity {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    public ExampleEntity(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
