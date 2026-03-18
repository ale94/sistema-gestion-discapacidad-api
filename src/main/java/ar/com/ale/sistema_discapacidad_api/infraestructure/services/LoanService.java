package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.LoanRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.LoanResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.LoanEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.LoanRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.ILoanService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.LoanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Override
    public LoanResponse create(LoanRequest request) {

        var loanToPersist = LoanEntity.builder()
                .type(request.getType())
                .equipmentNumber(request.getEquipmentNumber())
                .dni(request.getDni())
                .applicant(request.getApplicant())
                .address(request.getAddress())
                .phone(request.getPhone())
                .year(String.valueOf(LocalDate.now().getYear()))
                .requestDate(LocalDate.now())
                .expiration(request.getExpiration())
                .returnDate(request.getReturnDate())
                .build();
        var loanPersisted = this.loanRepository.save(loanToPersist);
        return this.loanMapper.toResponse(loanPersisted);
    }

    @Override
    public List<LoanResponse> readAll() {
        return this.loanRepository.findAll()
                .stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    @Override
    public LoanResponse update(LoanRequest request, Long id) {
        var loanToUpdate = this.loanRepository.findById(id).orElseThrow();
        loanToUpdate.setType(request.getType());
        loanToUpdate.setEquipmentNumber(request.getEquipmentNumber());
        loanToUpdate.setDni(request.getDni());
        loanToUpdate.setApplicant(request.getApplicant());
        loanToUpdate.setAddress(request.getAddress());
        loanToUpdate.setPhone(request.getPhone());
        loanToUpdate.setReturnDate(request.getReturnDate());
        var loanUpdated = this.loanRepository.save(loanToUpdate);
        return this.loanMapper.toResponse(loanUpdated);
    }

    @Override
    public void delete(Long id) {
        var loanToDelete = this.loanRepository.findById(id).orElseThrow();
        this.loanRepository.delete(loanToDelete);
    }
}
