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

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentTypeRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentTypeResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IEquipmentTypeService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/equipment-types")
@RequiredArgsConstructor
public class EquipmentTypeController {

    private final IEquipmentTypeService equipmentTypeService;

    @PostMapping
    ResponseEntity<EquipmentTypeResponse> save(@RequestBody EquipmentTypeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.equipmentTypeService.create(request));
    }

    @GetMapping
    ResponseEntity<List<EquipmentTypeResponse>> getAll() {
        return ResponseEntity.ok(this.equipmentTypeService.readAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<EquipmentTypeResponse> update(@RequestBody EquipmentTypeRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.equipmentTypeService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.equipmentTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
