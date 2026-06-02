package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRenewalRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassRenewalResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.services.FreePassRenewalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/free-pass-renewals")
@RequiredArgsConstructor
public class FreePassRenewalController {

    private final FreePassRenewalService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FreePassRenewalResponse create(
            @RequestBody
            FreePassRenewalRequest request
    ) {
        return service.create(request);
    }
}