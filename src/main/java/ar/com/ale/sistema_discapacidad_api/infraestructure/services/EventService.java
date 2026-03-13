package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EventRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EventResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EventEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.EventRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IEventService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.EventMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventResponse create(EventRequest request) {
        var eventToPersist = EventEntity.builder()
                .name(request.getName())
                .type(request.getType())
                .date(request.getDate())
                .description(request.getDescription())
                .attendees(request.getAttendees())
                .build();

        var eventPersisted = this.eventRepository.save(eventToPersist);
        return this.eventMapper.toResponse(eventPersisted);
    }

    @Override
    public List<EventResponse> readAll() {
        return this.eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    @Override
    public EventResponse update(EventRequest request, Long id) {
        var eventToUpdate = this.eventRepository.findById(id).orElseThrow();
        eventToUpdate.setName(request.getName());
        eventToUpdate.setType(request.getType());
        eventToUpdate.setDate(request.getDate());
        eventToUpdate.setDescription(request.getDescription());
        eventToUpdate.setAttendees(request.getAttendees());
        var eventUpdated = this.eventRepository.save(eventToUpdate);
        return this.eventMapper.toResponse(eventUpdated);
    }

    @Override
    public void delete(Long id) {
        var eventToDelete = this.eventRepository.findById(id).orElseThrow();
        this.eventRepository.delete(eventToDelete);
    }

}
