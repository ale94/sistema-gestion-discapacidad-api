package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonTrackingRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonTrackingResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IPersonTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/persons-tracking")
@RequiredArgsConstructor
public class PersonTrackingController {

    private final IPersonTrackingService personTrackingService;

    @PostMapping
    ResponseEntity<PersonTrackingResponse> save(@RequestBody PersonTrackingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personTrackingService.create(request));
    }

    @GetMapping
    ResponseEntity<List<PersonTrackingResponse>> getAll() {
        return ResponseEntity.ok(this.personTrackingService.readAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<PersonTrackingResponse> update(@RequestBody PersonTrackingRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.personTrackingService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personTrackingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
