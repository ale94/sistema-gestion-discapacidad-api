package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.UserRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.UserResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.UserEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.UserRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IUserService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse create(UserRequest request) {
        var userToPersist = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .dni(request.getDni())
                .role(request.getRole())
                .active(true)
                .build();
        var userPersisted = userRepository.save(userToPersist);
        return this.userMapper.toResponse(userPersisted);
    }

    @Override
    public List<UserResponse> readAll() {
        return this.userRepository.findAll()
                .stream()
                .map(this.userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        var userToUpdate = this.userRepository.findById(id).orElseThrow();
        userToUpdate.setFirstName(request.getFirstName());
        userToUpdate.setLastName(request.getLastName());
        userToUpdate.setPassword(request.getPassword());
        userToUpdate.setDni(request.getDni());
        userToUpdate.setRole(request.getRole());
        var userUpdated = userRepository.save(userToUpdate);
        return this.userMapper.toResponse(userUpdated);
    }

    @Override
    public void delete(Long id) {
        var userToDelete = this.userRepository.findById(id).orElseThrow();
        this.userRepository.delete(userToDelete);
    }
}
