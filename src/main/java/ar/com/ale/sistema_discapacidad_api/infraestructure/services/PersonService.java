package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.FamilyMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.PersonMapper;
import ar.com.ale.sistema_discapacidad_api.util.enums.Status;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class PersonService implements IPersonService {

	private final PersonRepository personRepository;
	private final PersonMapper personMapper;
	private final FamilyMapper familyMapper;

	@Override
	public PersonResponse create(PersonRegisterRequest request) {

		var address = AddressEntity.builder()
				.street(request.getStreet())
				.district(request.getDistrict())
				.locality(request.getLocality())
				.province(request.getProvince())
				.build();

		var education = EducationEntity.builder()
				.name(request.getSchoolName())
				.address(request.getEducationAddress())
				.educationLevel(request.getEducationLevel())
				.build();

		var work = WorkEntity.builder()
				.companyName(request.getCompanyName())
				.status(request.getEmploymentStatus())
				.address(request.getWorkAddress())
				.socialWork(request.getSocialWork())
				.nameSocialWork(request.getNameSocialWork())
				.build();

		var health = HealthEntity.builder()
				.cudNumber(request.getCudNumber())
				.activeCud(request.getActiveCud())
				.rehabilitationTreatment(request.getRehabilitationTreatment())
				.diagnostic(request.getDiagnostic())
				.disabilityType(request.getDisabilityType())
				.build();

		var benefit = BenefitEntity.builder()
				.federalProgram(request.getFederalProgram())
				.pension(request.getPension())
				.auh(request.getAuh())
				.merchandise(request.getMerchandise())
				.freePass(request.getFreePass())
				.build();

		var personToPersist = PersonEntity.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.dni(request.getDni())
				.status(Status.registrado)
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

		var address = personToUpdate.getAddress();
		address.setStreet(request.getStreet());
		address.setDistrict(request.getDistrict());
		address.setLocality(request.getLocality());
		address.setProvince(request.getProvince());

		var education = personToUpdate.getEducation();
		education.setName(request.getSchoolName());
		education.setAddress(request.getEducationAddress());
		education.setEducationLevel(request.getEducationLevel());

		var work = personToUpdate.getWork();
		work.setCompanyName(request.getCompanyName());
		work.setStatus(request.getEmploymentStatus());
		work.setAddress(request.getWorkAddress());
		work.setSocialWork(request.getSocialWork());
		work.setNameSocialWork(request.getNameSocialWork());

		var health = personToUpdate.getHealth();
		health.setCudNumber(request.getCudNumber());
		health.setActiveCud(request.getActiveCud());
		health.setRehabilitationTreatment(request.getRehabilitationTreatment());
		health.setDiagnostic(request.getDiagnostic());
		health.setDisabilityType(request.getDisabilityType());

		var benefit = personToUpdate.getBenefit();
		benefit.setFederalProgram(request.getFederalProgram());
		benefit.setPension(request.getPension());
		benefit.setAuh(request.getAuh());
		benefit.setMerchandise(request.getMerchandise());
		benefit.setFreePass(request.getFreePass());

		personToUpdate.getFamilyMembers().clear();

		request.getFamilyMembers()
				.stream()
				.map(familyMapper::toEntity)
				.forEach(personToUpdate::addFamilyMember);

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
