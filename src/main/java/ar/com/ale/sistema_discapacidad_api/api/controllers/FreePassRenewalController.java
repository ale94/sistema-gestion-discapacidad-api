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
@CrossOrigin(origins = "http://localhost:4200")
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
}