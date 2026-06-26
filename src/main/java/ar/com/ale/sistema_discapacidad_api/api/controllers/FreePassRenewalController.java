package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRenewalRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassRenewalResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IFreePassRenewalService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/free-pass-renewals")
@RequiredArgsConstructor
public class FreePassRenewalController {

    private final IFreePassRenewalService renewalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FreePassRenewalResponse create( @RequestBody FreePassRenewalRequest request ) {
        return this.renewalService.create(request);
    }

    @GetMapping
    ResponseEntity<List<FreePassRenewalResponse>> getAll() {
        return ResponseEntity.ok(this.renewalService.readAll());
    }

    @GetMapping("/free-pass/{freePassId}")
    public List<FreePassRenewalResponse> getByFreePassId(@PathVariable Long freePassId) {
        return this.renewalService.readByFreePassId(freePassId);
    }

    @GetMapping("/{id}")
    ResponseEntity<FreePassRenewalResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.renewalService.getById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<FreePassRenewalResponse> update(@RequestBody FreePassRenewalRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.renewalService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.renewalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}