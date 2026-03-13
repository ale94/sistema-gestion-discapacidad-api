package ar.com.ale.sistema_discapacidad_api.api.controllers;

import java.util.List;

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

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EventRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EventResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IEventService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final IEventService eventService;

    @PostMapping
    ResponseEntity<EventResponse> save(@RequestBody EventRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.eventService.create(request));
    }

    @GetMapping
    ResponseEntity<List<EventResponse>> getAll() {
        return ResponseEntity.ok(this.eventService.readAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<EventResponse> update(@RequestBody EventRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.eventService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
