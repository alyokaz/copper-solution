package copper.co.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class ExampleController {

	@Autowired
	EntityService entityService;

    @GetMapping(path = "/ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("/users")
	public List<ExampleEntity> getAll() {
		return entityService.getAllEntities();
	}

	@GetMapping("/users/{id}")
	public ExampleEntity get(@PathVariable Long id) {
		return entityService.getEntity(id).get();
	}

	@GetMapping("/users/name/first/{name}")
	public ResponseEntity<ExampleEntity> getByFirstName(@PathVariable String name) {
		return ResponseEntity.ok(entityService.getEntityByFirstName(name));
	}

	@GetMapping("/users/name/last/{name}")
	public ResponseEntity<ExampleEntity> getByLastName(@PathVariable String name) {
		return ResponseEntity.ok(entityService.getByLastName(name));
	}

	@GetMapping("/users/email/{email}")
	public ResponseEntity<ExampleEntity> getByEmail(@PathVariable String email) {
		return ResponseEntity.ok(entityService.getByEmail(email));
	}

	@PostMapping("/users")
	public ResponseEntity<ExampleEntity> save(@RequestBody ExampleEntity entity) {
		return ResponseEntity.ok(entityService.save(entity));
	}




}
