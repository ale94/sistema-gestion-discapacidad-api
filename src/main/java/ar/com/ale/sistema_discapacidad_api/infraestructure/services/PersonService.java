package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.AddressResponse;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.BenefitResponse;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EducationResponse;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.HealthResponse;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.WorkResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.AddressEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.BenefitEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EducationEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.HealthEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.WorkEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IPersonService;
import ar.com.ale.sistema_discapacidad_api.util.enums.Status;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class PersonService implements IPersonService {

        private final PersonRepository personRepository;

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

                education.setPerson(personToPersist);
                work.setPerson(personToPersist);
                health.setPerson(personToPersist);
                benefit.setPerson(personToPersist);
                address.setPerson(personToPersist);

                var personPersisted = this.personRepository.save(personToPersist);
                return this.entityToResponse(personPersisted);
        }

        private PersonResponse entityToResponse(PersonEntity entity) {
                var response = new PersonResponse();
                BeanUtils.copyProperties(entity, response);

                var addressResponse = new AddressResponse();
                BeanUtils.copyProperties(entity.getAddress(), addressResponse);
                response.setAddress(addressResponse);

                var educationResponse = new EducationResponse();
                BeanUtils.copyProperties(entity.getEducation(), educationResponse);
                response.setEducation(educationResponse);

                var workResponse = new WorkResponse();
                BeanUtils.copyProperties(entity.getWork(), workResponse);
                response.setWork(workResponse);

                var healthResponse = new HealthResponse();
                BeanUtils.copyProperties(entity.getHealth(), healthResponse);
                response.setHealth(healthResponse);

                var benefitResponse = new BenefitResponse();
                BeanUtils.copyProperties(entity.getBenefit(), benefitResponse);
                response.setBenefit(benefitResponse);

                return response;
        }

}
