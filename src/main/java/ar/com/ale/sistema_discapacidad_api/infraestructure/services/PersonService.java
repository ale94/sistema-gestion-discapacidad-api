package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.AddressEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.BenefitEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EducationEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.HealthEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.WorkEntity;
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
		// Buscamos la persona existente
		var personToUpdate = this.personRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
					HttpStatus.NOT_FOUND,"Persona no encontrada con ID: " + id)
				);

		// Manejo seguro de la colección de familiares (Relación OneToMany)
		if (personToUpdate.getFamilyMembers() != null) {
			personToUpdate.getFamilyMembers().clear();
		}
		if (request.getFamilyMembers() != null) {
			request.getFamilyMembers()
					.stream()
					.map(familyMapper::toEntity)
					.forEach(familyMember -> {
						familyMember.setPerson(personToUpdate); // Es crucial mantener la relación bidireccional
						personToUpdate.addFamilyMember(familyMember);
					});
		}

		// Actualización segura de Dirección (OneToOne)
		if (request.getAddress() != null) {
			var address = personToUpdate.getAddress();
			if (address == null) {
				address = new AddressEntity();
				address.setPerson(personToUpdate);
			}
			address.setStreet(request.getAddress().getStreet());
			address.setDistrict(request.getAddress().getDistrict());
			address.setLocality(request.getAddress().getLocality());
			address.setProvince(request.getAddress().getProvince());
			personToUpdate.setAddress(address);
		}

		// Actualización segura de Trabajo (OneToOne)
		if (request.getWork() != null) {
			var work = personToUpdate.getWork();
			if (work == null) {
				work = new WorkEntity();
				work.setPerson(personToUpdate);
			}
			work.setCompanyName(request.getWork().getCompanyName());
			work.setStatus(request.getWork().getStatus());
			work.setAddress(request.getWork().getAddress());
			work.setSocialWork(request.getWork().getSocialWork());
			work.setNameSocialWork(request.getWork().getNameSocialWork());
			personToUpdate.setWork(work);
		}

		// Actualización segura de Educación (OneToOne)
		if (request.getEducation() != null) {
			var education = personToUpdate.getEducation();
			if (education == null) {
				education = new EducationEntity();
				education.setPerson(personToUpdate);
			}
			education.setName(request.getEducation().getName());
			education.setAddress(request.getEducation().getAddress());
			education.setEducationLevel(request.getEducation().getEducationLevel());
			personToUpdate.setEducation(education);
		}

		// Actualización segura de Salud (OneToOne)
		if (request.getHealth() != null) {
			var health = personToUpdate.getHealth();
			if (health == null) {
				health = new HealthEntity();
				health.setPerson(personToUpdate);
			}
			health.setCudNumber(request.getHealth().getCudNumber());
			health.setActiveCud(request.getHealth().getActiveCud());
			health.setRehabilitationTreatment(request.getHealth().getRehabilitationTreatment());
			health.setDiagnostic(request.getHealth().getDiagnostic());
			health.setDisabilityType(request.getHealth().getDisabilityType());
			personToUpdate.setHealth(health);
		}

		// Actualización segura de Beneficios (OneToOne)
		if (request.getBenefit() != null) {
			var benefit = personToUpdate.getBenefit();
			if (benefit == null) {
				benefit = new BenefitEntity();
				benefit.setPerson(personToUpdate);
			}
			benefit.setFederalProgram(request.getBenefit().getFederalProgram());
			benefit.setPension(request.getBenefit().getPension());
			benefit.setAuh(request.getBenefit().getAuh());
			benefit.setMerchandise(request.getBenefit().getMerchandise());
			benefit.setFreePass(request.getBenefit().getFreePass());
			personToUpdate.setBenefit(benefit);
		}

		// Campos básicos de la Persona
		personToUpdate.setFirstName(request.getFirstName());
		personToUpdate.setLastName(request.getLastName());
		personToUpdate.setDni(request.getDni());
		personToUpdate.setCivilStatus(request.getCivilStatus());
		personToUpdate.setDateBirth(request.getDateBirth());
		personToUpdate.setTutor(request.getTutor());
		personToUpdate.setPhone(request.getPhone());
		personToUpdate.setGender(request.getGender());

		// Guardar cambios
		var personToUpdated = this.personRepository.save(personToUpdate);

		return this.personMapper.toResponse(personToUpdated);
	}
	/*
	@Override
	public PersonResponse update(PersonRegisterRequest request, Long id) {
		var personToUpdate = this.personRepository.findById(id)
				.orElseThrow();

		personToUpdate.getFamilyMembers().clear();

		request.getFamilyMembers()
				.stream()
				.map(familyMapper::toEntity)
				.forEach(personToUpdate::addFamilyMember);

		var address = personToUpdate.getAddress();
		address.setStreet(request.getAddress().getStreet());
		address.setDistrict(request.getAddress().getDistrict());
		address.setLocality(request.getAddress().getLocality());
		address.setProvince(request.getAddress().getProvince());

		var work = personToUpdate.getWork();
		work.setCompanyName(request.getWork().getCompanyName());
		work.setStatus(request.getWork().getStatus());
		work.setAddress(request.getWork().getAddress());
		work.setSocialWork(request.getWork().getSocialWork());
		work.setNameSocialWork(request.getWork().getNameSocialWork());

		var education = personToUpdate.getEducation();
		education.setName(request.getEducation().getName());
		education.setAddress(request.getEducation().getAddress());
		education.setEducationLevel(request.getEducation().getEducationLevel());

		var health = personToUpdate.getHealth();
		health.setCudNumber(request.getHealth().getCudNumber());
		health.setActiveCud(request.getHealth().getActiveCud());
		health.setRehabilitationTreatment(request.getHealth().getRehabilitationTreatment());
		health.setDiagnostic(request.getHealth().getDiagnostic());
		health.setDisabilityType(request.getHealth().getDisabilityType());

		var benefit = personToUpdate.getBenefit();
		benefit.setFederalProgram(request.getBenefit().getFederalProgram());
		benefit.setPension(request.getBenefit().getPension());
		benefit.setAuh(request.getBenefit().getAuh());
		benefit.setMerchandise(request.getBenefit().getMerchandise());
		benefit.setFreePass(request.getBenefit().getFreePass());

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
	}*/

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

	@Override
	public PersonResponse findByDni(Long dni) {
		var person = this.personRepository.findByDni(dni)
				.orElseThrow(() -> new RuntimeException("Persona no encontrada con DNI: " + dni));
		return this.personMapper.toResponse(person);
	}

}
