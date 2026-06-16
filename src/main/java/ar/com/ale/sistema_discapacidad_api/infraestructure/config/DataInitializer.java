package ar.com.ale.sistema_discapacidad_api.infraestructure.config;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.*;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonTrackingEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.LoanEntity;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.*;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final PersonTrackingRepository personTrackingRepository;
    private final LoanRepository loanRepository;
    private final ar.com.ale.sistema_discapacidad_api.infraestructure.services.UserService userService;
    private final IPersonService personService;
    private final IFreePassService freePassService;
    private final INationalFreePassService nationalFreePassService;
    private final IFreePassRenewalService freePassRenewalService;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            seedUsers();
        }
        if (personRepository.count() == 0) {
            seedPersonsAndDerivedData();
        }
    }

    private void seedUsers() {
        var admin = UserRequest.builder()
                .firstName("Maria Eugenia").lastName("Velasquez")
                .dni("34091232").password("admin").role("ADMIN").build();
        userService.create(admin);

        var user1 = UserRequest.builder()
                .firstName("Celeste Licia").lastName("Gutiérrez")
                .dni("33183555").password("1234").role("USER").build();
        userService.create(user1);

        var user2 = UserRequest.builder()
                .firstName("Tamara Zoe").lastName("Zambrano")
                .dni("41902113").password("1234").role("USER").build();
        userService.create(user2);

        var user3 = UserRequest.builder()
                .firstName("Fernanda Antonella").lastName("Cerpa")
                .dni("39201327").password("1234").role("USER").build();
        userService.create(user3);
    }

    private void seedPersonsAndDerivedData() {
        var p1 = personService.create(PersonRegisterRequest.builder()
                .firstName("Juan Carlos").lastName("Mendoza")
                .dni(30123456L).civilStatus("SOLTERO")
                .dateBirth(LocalDate.of(1985, 3, 15))
                .phone(1145678901L).gender("MASCULINO")
                .tutor("N/A")
                .address(AddressRequest.builder().street("Av. Rivadavia 1234").district("Flores").locality("CABA").province("Buenos Aires").build())
                .education(EducationRequest.builder().name("Escuela N° 4").address("Calle Falsa 456").educationLevel("SECUNDARIO").build())
                .work(WorkRequest.builder().companyName("Empleos S.A.").status("ACTIVO").address("Av. Corrientes 789").socialWork(true).nameSocialWork("OSECAC").build())
                .health(HealthRequest.builder().cudNumber("CUD-2020-001").activeCud(true).rehabilitationTreatment(true).diagnostic("Trastorno del espectro autista").disabilityType("MENTAL").build())
                .benefit(BenefitRequest.builder().federalProgram(true).pension(false).auh(true).merchandise(false).freePass(true).build())
                .build());

        var p2 = personService.create(PersonRegisterRequest.builder()
                .firstName("María Laura").lastName("Giménez")
                .dni(27111222L).civilStatus("CASADA")
                .dateBirth(LocalDate.of(1990, 7, 22))
                .phone(1156789012L).gender("FEMENINO")
                .tutor("N/A")
                .address(AddressRequest.builder().street("Belgrano 567").district("Centro").locality("Córdoba").province("Córdoba").build())
                .education(EducationRequest.builder().name("Universidad Nacional de Córdoba").address("Av. Vélez Sarsfield 1600").educationLevel("UNIVERSITARIO").build())
                .work(WorkRequest.builder().companyName("Municipalidad de Córdoba").status("ACTIVO").address("Caseros 551").socialWork(true).nameSocialWork("Apross").build())
                .health(HealthRequest.builder().cudNumber("CUD-2021-002").activeCud(true).rehabilitationTreatment(false).diagnostic("Discapacidad motriz").disabilityType("MOTORA").build())
                .benefit(BenefitRequest.builder().federalProgram(false).pension(true).auh(false).merchandise(true).freePass(true).build())
                .build());

        var p3 = personService.create(PersonRegisterRequest.builder()
                .firstName("Roberto").lastName("Álvarez")
                .dni(22333444L).civilStatus("DIVORCIADO")
                .dateBirth(LocalDate.of(1978, 11, 8))
                .phone(1167890123L).gender("MASCULINO")
                .tutor("N/A")
                .address(AddressRequest.builder().street("San Martín 890").district("Godoy Cruz").locality("Godoy Cruz").province("Mendoza").build())
                .education(EducationRequest.builder().name("Escuela Técnica N° 3").address("Lavalle 345").educationLevel("TERCIARIO").build())
                .work(WorkRequest.builder().companyName("Independiente").status("MONOTRIBUTISTA").address("San Martín 890").socialWork(false).nameSocialWork("N/A").build())
                .health(HealthRequest.builder().cudNumber("CUD-2022-003").activeCud(true).rehabilitationTreatment(true).diagnostic("Hipoacusia bilateral").disabilityType("AUDITIVA").build())
                .benefit(BenefitRequest.builder().federalProgram(true).pension(true).auh(false).merchandise(false).freePass(true).build())
                .build());

        var p4 = personService.create(PersonRegisterRequest.builder()
                .firstName("Ana Patricia").lastName("López")
                .dni(33444555L).civilStatus("SOLTERA")
                .dateBirth(LocalDate.of(2000, 5, 30))
                .phone(1178901234L).gender("FEMENINO")
                .tutor("Sra. Patricia López")
                .address(AddressRequest.builder().street("Urquiza 123").district("Paraná").locality("Paraná").province("Entre Ríos").build())
                .education(EducationRequest.builder().name("Escuela Especial N° 2").address("Alsina 456").educationLevel("PRIMARIO").build())
                .work(WorkRequest.builder().companyName("N/A").status("INACTIVO").address("N/A").socialWork(false).nameSocialWork("N/A").build())
                .health(HealthRequest.builder().cudNumber("CUD-2023-004").activeCud(true).rehabilitationTreatment(true).diagnostic("Síndrome de Down").disabilityType("MENTAL").build())
                .benefit(BenefitRequest.builder().federalProgram(true).pension(true).auh(false).merchandise(false).freePass(true).build())
                .build());

        var p5 = personService.create(PersonRegisterRequest.builder()
                .firstName("Carlos Alberto").lastName("Fernández")
                .dni(25555666L).civilStatus("CASADO")
                .dateBirth(LocalDate.of(1982, 1, 14))
                .phone(1189012345L).gender("MASCULINO")
                .tutor("N/A")
                .address(AddressRequest.builder().street("Mitre 2345").district("La Plata").locality("La Plata").province("Buenos Aires").build())
                .education(EducationRequest.builder().name("Universidad Nacional de La Plata").address("Av. 7 776").educationLevel("UNIVERSITARIO").build())
                .work(WorkRequest.builder().companyName("Tech Solutions").status("ACTIVO").address("Calle 8 1234").socialWork(true).nameSocialWork("IOMA").build())
                .health(HealthRequest.builder().cudNumber("CUD-2019-005").activeCud(false).rehabilitationTreatment(false).diagnostic("Discapacidad visual").disabilityType("VISUAL").build())
                .benefit(BenefitRequest.builder().federalProgram(false).pension(false).auh(false).merchandise(true).freePass(false).build())
                .build());

        var fp1 = new FreePassRequest();
        fp1.setPersonId(p1.getId());
        fp1.setReason("Traslado a terapias de rehabilitación semanales");
        fp1.setStatus(FreePassStatus.APROBADO);
        var fp1Response = freePassService.create(fp1);

        var fp2 = new FreePassRequest();
        fp2.setPersonId(p2.getId());
        fp2.setReason("Traslado al centro de día");
        fp2.setStatus(FreePassStatus.APROBADO);
        var fp2Response = freePassService.create(fp2);

        var fp3 = new FreePassRequest();
        fp3.setPersonId(p3.getId());
        fp3.setReason("Traslado a tratamiento auditivo");
        fp3.setStatus(FreePassStatus.PENDIENTE);
        var fp3Response = freePassService.create(fp3);

        var fp4 = new FreePassRequest();
        fp4.setPersonId(p4.getId());
        fp4.setReason("Traslado a escuela especial y terapias");
        fp4.setStatus(FreePassStatus.APROBADO);
        var fp4Response = freePassService.create(fp4);

        var nfp1 = new NationalFreePassRequest();
        nfp1.setPersonId(p1.getId());
        nfp1.setTripDate(LocalDate.of(2026, 3, 10));
        nfp1.setTicketQuantity(2);
        nfp1.setOrigin("Buenos Aires");
        nfp1.setDestination("Mar del Plata");
        nfp1.setStatus(FreePassStatus.APROBADO);
        nfp1.setReason("Viaje a rehabilitación");
        nationalFreePassService.create(nfp1);

        var nfp2 = new NationalFreePassRequest();
        nfp2.setPersonId(p2.getId());
        nfp2.setTripDate(LocalDate.of(2026, 5, 15));
        nfp2.setTicketQuantity(3);
        nfp2.setOrigin("Córdoba");
        nfp2.setDestination("Buenos Aires");
        nfp2.setStatus(FreePassStatus.PENDIENTE);
        nfp2.setReason("Tratamiento médico especializado");
        nationalFreePassService.create(nfp2);

        var renewal1 = new FreePassRenewalRequest();
        renewal1.setFreePassId(fp1Response.getId());
        renewal1.setYear(2026);
        freePassRenewalService.create(renewal1);

        var renewal2 = new FreePassRenewalRequest();
        renewal2.setFreePassId(fp2Response.getId());
        renewal2.setYear(2026);
        freePassRenewalService.create(renewal2);

        var renewal3 = new FreePassRenewalRequest();
        renewal3.setFreePassId(fp4Response.getId());
        renewal3.setYear(2026);
        freePassRenewalService.create(renewal3);

        seedPersonTracking();
        seedLoans();
    }

    private void seedPersonTracking() {
        personTrackingRepository.save(PersonTrackingEntity.builder()
                .lastName("Mendoza").firstName("Juan Carlos").dni(30123456L)
                .indicatorType("CUD").address("Av. Rivadavia 1234, Flores, CABA")
                .phone(1145678901L).build());

        personTrackingRepository.save(PersonTrackingEntity.builder()
                .lastName("Giménez").firstName("María Laura").dni(27111222L)
                .indicatorType("CUD").address("Belgrano 567, Centro, Córdoba")
                .phone(1156789012L).build());

        personTrackingRepository.save(PersonTrackingEntity.builder()
                .lastName("Álvarez").firstName("Roberto").dni(22333444L)
                .indicatorType("PASE_LIBRE").address("San Martín 890, Godoy Cruz, Mendoza")
                .phone(1167890123L).build());

        personTrackingRepository.save(PersonTrackingEntity.builder()
                .lastName("López").firstName("Ana Patricia").dni(33444555L)
                .indicatorType("CUD").address("Urquiza 123, Paraná, Entre Ríos")
                .phone(1178901234L).build());

        personTrackingRepository.save(PersonTrackingEntity.builder()
                .lastName("Fernández").firstName("Carlos Alberto").dni(25555666L)
                .indicatorType("PENSION").address("Mitre 2345, La Plata, Buenos Aires")
                .phone(1189012345L).build());
    }

    private void seedLoans() {
        loanRepository.save(LoanEntity.builder()
                .type("SILLA_DE_RUEDAS").equipmentNumber("SR-2026-001")
                .dni(30123456L).applicant("Juan Carlos Mendoza")
                .address("Av. Rivadavia 1234, Flores, CABA").phone(1145678901L)
                .year("2026").requestDate(LocalDate.of(2026, 1, 15))
                .expiration(LocalDate.of(2026, 7, 15))
                .returnDate(null).build());

        loanRepository.save(LoanEntity.builder()
                .type("MULETA").equipmentNumber("MU-2026-002")
                .dni(27111222L).applicant("María Laura Giménez")
                .address("Belgrano 567, Centro, Córdoba").phone(1156789012L)
                .year("2026").requestDate(LocalDate.of(2026, 2, 20))
                .expiration(LocalDate.of(2026, 8, 20))
                .returnDate(LocalDate.of(2026, 5, 10)).build());

        loanRepository.save(LoanEntity.builder()
                .type("ANDADOR").equipmentNumber("AN-2026-003")
                .dni(22333444L).applicant("Roberto Álvarez")
                .address("San Martín 890, Godoy Cruz, Mendoza").phone(1167890123L)
                .year("2026").requestDate(LocalDate.of(2026, 3, 5))
                .expiration(LocalDate.of(2026, 9, 5))
                .returnDate(null).build());

        loanRepository.save(LoanEntity.builder()
                .type("SILLA_DE_RUEDAS").equipmentNumber("SR-2026-004")
                .dni(33444555L).applicant("Ana Patricia López")
                .address("Urquiza 123, Paraná, Entre Ríos").phone(1178901234L)
                .year("2026").requestDate(LocalDate.of(2026, 4, 10))
                .expiration(LocalDate.of(2026, 10, 10))
                .returnDate(null).build());
    }
}
