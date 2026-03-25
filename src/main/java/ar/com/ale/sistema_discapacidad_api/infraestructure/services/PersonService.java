package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IPersonService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.AddressMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.BenefitMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.EducationMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.FamilyMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.HealthMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.PersonMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.WorkMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class PersonService implements IPersonService {

	private final PersonRepository personRepository;
	private final PersonMapper personMapper;
	private final FamilyMapper familyMapper;
	private final EducationMapper educationMapper;
	private final AddressMapper addressMapper;
	private final WorkMapper workMapper;
	private final HealthMapper healthMapper;
	private final BenefitMapper benefitMapper;

	@Override
	public PersonResponse create(PersonRegisterRequest request) {

		var address = this.addressMapper.toEntity(request.getAddress());
		var work = this.workMapper.toEntity(request.getWork());
		var education = this.educationMapper.toEntity(request.getEducation());
		var health = this.healthMapper.toEntity(request.getHealth());
		var benefit = this.benefitMapper.toEntity(request.getBenefit());

		var personToPersist = PersonEntity.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.dni(request.getDni())
				.civilStatus(request.getCivilStatus())
				.dateBirth(request.getDateBirth())
				.tutor(request.getTutor())
				.phone(request.getPhone())
				.gender(request.getGender())
				.registrationDate(LocalDate.now())
				.address(address)
				.education(education)
				.work(work)
				.health(health)
				.benefit(benefit)
				.build();

		if (request.getFamilyMembers() != null) {
			personToPersist.setFamilyMembers(request.getFamilyMembers()
					.stream()
					.map(familyMemberRequest -> {
						var familyMember = familyMapper.toEntity(familyMemberRequest);
						familyMember.setPerson(personToPersist);
						return familyMember;
					})
					.toList());
		}

		education.setPerson(personToPersist);
		work.setPerson(personToPersist);
		health.setPerson(personToPersist);
		benefit.setPerson(personToPersist);
		address.setPerson(personToPersist);

		var personPersisted = this.personRepository.save(personToPersist);
		return this.personMapper.toResponse(personPersisted);
	}

	@Override
	public PersonResponse update(PersonRegisterRequest request, Long id) {
		var personToUpdate = this.personRepository.findById(id)
				.orElseThrow();

		var address = this.addressMapper.toEntity(request.getAddress());
		var work = this.workMapper.toEntity(request.getWork());
		var education = this.educationMapper.toEntity(request.getEducation());
		var health = this.healthMapper.toEntity(request.getHealth());
		var benefit = this.benefitMapper.toEntity(request.getBenefit());

		personToUpdate.getFamilyMembers().clear();

		request.getFamilyMembers()
				.stream()
				.map(familyMapper::toEntity)
				.forEach(personToUpdate::addFamilyMember);

		personToUpdate.setAddress(address);
		personToUpdate.setWork(work);
		personToUpdate.setEducation(education);
		personToUpdate.setHealth(health);
		personToUpdate.setBenefit(benefit);
		personToUpdate.setFirstName(request.getFirstName());
		personToUpdate.setLastName(request.getLastName());
		personToUpdate.setDni(request.getDni());
		personToUpdate.setCivilStatus(request.getCivilStatus());
		personToUpdate.setDateBirth(request.getDateBirth());
		personToUpdate.setTutor(request.getTutor());
		personToUpdate.setPhone(request.getPhone());
		personToUpdate.setGender(request.getGender());

		var personToUpdated = this.personRepository.save(personToUpdate);

		return this.personMapper.toResponse(personToUpdated);
	}

	@Override
	public List<PersonResponse> readAll() {
		return this.personRepository.findAll()
				.stream()
				.map(personMapper::toResponse)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		var personToDelete = this.personRepository.findById(id).orElseThrow();
		this.personRepository.delete(personToDelete);
	}

}
