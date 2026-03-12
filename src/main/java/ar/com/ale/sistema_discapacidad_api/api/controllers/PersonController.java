package ar.com.ale.sistema_discapacidad_api.api.controllers;

import java.util.List;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonTrackingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IPersonService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final IPersonService personService;

    @PostMapping
    ResponseEntity<PersonResponse> save(@RequestBody PersonRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.create(request));
    }

    @GetMapping
    ResponseEntity<List<PersonResponse>> getAll() {
        return ResponseEntity.ok(this.personService.readAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<PersonResponse> update(@RequestBody PersonRegisterRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.personService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
