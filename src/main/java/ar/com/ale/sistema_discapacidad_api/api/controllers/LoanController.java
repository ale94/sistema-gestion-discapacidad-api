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

import ar.com.ale.sistema_discapacidad_api.api.models.requests.LoanRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.LoanResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.ILoanService;
import lombok.RequiredArgsConstructor;

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
