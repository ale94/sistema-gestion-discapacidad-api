package ar.com.ale.sistema_discapacidad_api;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.com.ale.sistema_discapacidad_api.domain.entities.AddressEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.BenefitEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EducationEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.HealthEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.WorkEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.AddressRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.BenefitRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.EducationRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.FamilyMemberRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.HealthRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.WorkRepository;
import ar.com.ale.sistema_discapacidad_api.util.enums.Status;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class SistemaDiscapacidadApiApplication implements CommandLineRunner {

	private final PersonRepository personRepository;
	private final AddressRepository addressRepository;
	private final BenefitRepository benefitRepository;
	private final EducationRepository educationRepository;
	private final FamilyMemberRepository familyMemberRepository;
	private final WorkRepository workRepository;
	private final HealthRepository healthRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaDiscapacidadApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var address = AddressEntity.builder()
				.street("Los constituyentes 123")
				.district("San Francisco M.228 L.12")
				.locality("Ledesma")
				.province("Jujuy")
				.build();

		var education = EducationEntity.builder()
				.name("Escuela de Comercio 4")
				.address("av. libertador 123")
				.educationLevel("Secundario")
				.build();

		var work = WorkEntity.builder()
				.companyName("Empresa Ledesma")
				.address("Ingenio")
				.status("EMPLEADO")
				.socialWork(true)
				.nameSocialWork("OSDE")
				.build();

		var health = HealthEntity.builder()
				.cudNumber("123456")
				.activeCud(true)
				.rehabilitationTreatment(true)
				.diagnostic("Neurodivergente")
				.disabilityType("Multiple")
				.build();

		var benefit = BenefitEntity.builder()
				.federalProgram(true)
				.pension(false)
				.auh(false)
				.merchandise(false)
				.freePass(false)
				.build();

		educationRepository.save(education);
		workRepository.save(work);
		addressRepository.save(address);
		healthRepository.save(health);
		benefitRepository.save(benefit);

		var person = PersonEntity.builder()
				.firstName("Alejandro")
				.lastName("Rua")
				.dni("12345678")
				.civilStatus("SOTLERO")
				.dateBirth(LocalDate.now())
				.tutor("Papi")
				.phone("3886565656")
				.gender("Masculino")
				.registrationDate(LocalDate.now())
				.status(Status.registrado)
				.indicatorType("Diabetico")
				.consultationDate(LocalDate.now())
				.education(education)
				.work(work)
				.health(health)
				.address(address)
				.benefit(benefit)
				.build();

		personRepository.save(person);

	}

}
