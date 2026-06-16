package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IFreePassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/free-passes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FreePassController {

    private final IFreePassService freePassService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FreePassResponse create(
            @RequestBody FreePassRequest request) {

        return freePassService.create(request);
    }

    @GetMapping
    public List<FreePassResponse> getAll() {

        return freePassService.readAll();
    }

    @GetMapping("/{id}")
    public FreePassResponse getById(
            @PathVariable Long id) {

        return freePassService.getById(id);
    }

    @PutMapping("/{id}")
    public FreePassResponse update(
            @PathVariable Long id,
            @RequestBody FreePassRequest request) {

        return freePassService.update(request, id);
    }

    @PatchMapping("/{id}/status")
    public FreePassResponse updateStatus(
            @PathVariable Long id,
            @RequestBody FreePassStatusRequest request) {

        return freePassService.updateStatus(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        freePassService.delete(id);
    }
}