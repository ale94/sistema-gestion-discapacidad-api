package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EventRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.LoanRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EventResponse;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.LoanResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IEventService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final ILoanService loanService;

    @PostMapping
    ResponseEntity<LoanResponse> save(@RequestBody LoanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.loanService.create(request));
    }

    @GetMapping
    ResponseEntity<List<LoanResponse>> getAll() {
        return ResponseEntity.ok(this.loanService.readAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<LoanResponse> update(@RequestBody LoanRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.loanService.update(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
