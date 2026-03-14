package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentTypeRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentTypeResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EquipmentTypeEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.EquipmentTypeRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IEquipmentTypeService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.EquipmentTypeMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class EquipmentTypeService implements IEquipmentTypeService {

    private final EquipmentTypeRepository equipmentTypeRepository;
    private final EquipmentTypeMapper equipmentTypeMapper;

    @Override
    public EquipmentTypeResponse create(EquipmentTypeRequest request) {
        var equipmentTypeToPersist = EquipmentTypeEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        var equipmentTypePersisted = equipmentTypeRepository.save(equipmentTypeToPersist);
        return this.equipmentTypeMapper.toResponse(equipmentTypePersisted);
    }

    @Override
    public List<EquipmentTypeResponse> readAll() {
        return this.equipmentTypeRepository.findAll().stream()
                .map(this.equipmentTypeMapper::toResponse)
                .toList();
    }

    @Override
    public EquipmentTypeResponse update(EquipmentTypeRequest request, Long id) {
        var equipmentTypeToUpdate = this.equipmentTypeRepository.findById(id)
                .orElseThrow();
        equipmentTypeToUpdate.setName(request.getName());
        equipmentTypeToUpdate.setDescription(request.getDescription());
        var equipmentTypeUpdated = this.equipmentTypeRepository.save(equipmentTypeToUpdate);
        return this.equipmentTypeMapper.toResponse(equipmentTypeUpdated);
    }

    @Override
    public void delete(Long id) {
        var equipmentTypeToDelete = this.equipmentTypeRepository.findById(id)
                .orElseThrow();
        this.equipmentTypeRepository.delete(equipmentTypeToDelete);
    }

}
