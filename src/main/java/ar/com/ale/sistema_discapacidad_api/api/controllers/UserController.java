package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.UserRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.UserResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.create(request));
    }

    @GetMapping
    ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(this.userService.readAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> update(@RequestBody UserRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
