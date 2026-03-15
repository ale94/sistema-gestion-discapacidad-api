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

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IEquipmentService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final IEquipmentService equipmentService;

    @PostMapping
    ResponseEntity<EquipmentResponse> save(@RequestBody EquipmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.equipmentService.create(request));
    }

    @GetMapping
    ResponseEntity<List<EquipmentResponse>> getAll() {
        return ResponseEntity.ok(this.equipmentService.readAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<EquipmentResponse> update(@RequestBody EquipmentRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.equipmentService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
