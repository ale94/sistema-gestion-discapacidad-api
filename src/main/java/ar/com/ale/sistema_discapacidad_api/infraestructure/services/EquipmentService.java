package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EquipmentEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.EquipmentRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.EquipmentTypeRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IEquipmentService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.EquipmentMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class EquipmentService implements IEquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final EquipmentMapper equipmentMapper;
    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public EquipmentResponse create(EquipmentRequest request) {

        if (request.getIdEquipmentType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de equipo es requerido");
        }

        var equipmentType = equipmentTypeRepository.findById(
                request.getIdEquipmentType())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo de equipo no encontrado"));

        var equipmentToPersist = EquipmentEntity.builder()
                .code(generateCode())
                .totalStock(request.getTotalStock())
                .status(request.getStatus())
                .createdAt(LocalDate.now())
                .equipmentType(equipmentType)
                .build();
        var equipmentPersisted = equipmentRepository.save(equipmentToPersist);
        return this.equipmentMapper.toResponse(equipmentPersisted);
    }

    @Override
    public EquipmentResponse getById(Long id) {
        var equipment = this.equipmentRepository.findById(id).orElseThrow();
        return this.equipmentMapper.toResponse(equipment);
    }

    @Override
    public List<EquipmentResponse> readAll() {
        return this.equipmentRepository.findAll()
                .stream()
                .map(equipmentMapper::toResponse)
                .toList();
    }

    @Override
    public EquipmentResponse update(EquipmentRequest request, Long id) {

        if (request.getIdEquipmentType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de equipo es requerido");
        }

        var equipmentType = equipmentTypeRepository.findById(
                request.getIdEquipmentType())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo de equipo no encontrado"));

        var equipmentToUpdate = this.equipmentRepository.findById(id).orElseThrow();
        equipmentToUpdate.setTotalStock(request.getTotalStock());
        equipmentToUpdate.setStatus(request.getStatus());
        equipmentToUpdate.setEquipmentType(equipmentType);

        var equipmentUpdated = this.equipmentRepository.save(equipmentToUpdate);
        return this.equipmentMapper.toResponse(equipmentUpdated);

    }

    @Override
    public void delete(Long id) {
        var equipmentToDelete = equipmentRepository.findById(id).orElseThrow();
        this.equipmentRepository.delete(equipmentToDelete);
    }

    private String generateCode() {
        return "EQ" + counter.getAndIncrement();
    }

}
