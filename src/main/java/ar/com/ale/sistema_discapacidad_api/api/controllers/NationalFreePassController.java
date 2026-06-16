package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.NationalFreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.NationalFreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.NationalFreePassResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.INationalFreePassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/national-free-passes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NationalFreePassController {

    private final INationalFreePassService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NationalFreePassResponse create(
            @RequestBody NationalFreePassRequest request) {

        return service.create(request);
    }

    @GetMapping
    public List<NationalFreePassResponse> getAll() {

        return service.readAll();
    }

    @PutMapping("/{id}")
    public NationalFreePassResponse update(@PathVariable Long id,
            @RequestBody NationalFreePassRequest request) {

        return service.update(request, id);
    }

    @GetMapping("/{id}")
    public NationalFreePassResponse getById(
            @PathVariable Long id) {

        return service.readById(id);
    }

    @GetMapping("/person/{personId}")
    public List<NationalFreePassResponse> getByPerson(
            @PathVariable Long personId) {

        return service.readByPersonId(personId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }

    @PatchMapping("/{id}/status")
    public NationalFreePassResponse updateStatus(
            @PathVariable Long id,
            @RequestBody NationalFreePassStatusRequest request) {

        return service.updateStatus(id, request);
    }
}