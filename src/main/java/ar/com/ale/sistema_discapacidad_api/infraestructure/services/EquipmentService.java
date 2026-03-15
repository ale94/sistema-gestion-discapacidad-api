package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private static int counter = 1;

    @Override
    public EquipmentResponse create(EquipmentRequest request) {

        var equipmentType = equipmentTypeRepository.findById(Long.parseLong(request.getIdEquipmentType()))
                .orElseThrow();

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
    public List<EquipmentResponse> readAll() {
        return this.equipmentRepository.findAll()
                .stream()
                .map(equipmentMapper::toResponse)
                .toList();
    }

    @Override
    public EquipmentResponse update(EquipmentRequest request, Long id) {
        var equipmentType = equipmentTypeRepository.findById(Long.parseLong(request.getIdEquipmentType()))
                .orElseThrow();

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

    private static String generateCode() {
        return "EQ" + counter++;
    }

}
