INSERT INTO person (
        first_name,
        last_name,
        dni,
        civil_status,
        date_birth,
        tutor,
        phone,
        gender,
        registration_date,
        status
    )
VALUES (
        'Juan',
        'Perez',
        '30111222',
        'SOLTERO',
        '1990-05-10',
        'Maria Perez',
        '3884011111',
        'MASCULINO',
        '2024-01-10',
        'registrado'
    ),
    (
        'Ramon',
        'Fernandez',
        '12111278',
        'CASADO',
        '1993-05-10',
        'Gaston Perez',
        '3884011111',
        'MASCULINO',
        '2024-01-10',
        'registrado'
    ),
    (
        'Juan',
        'Gomez',
        '45114652',
        'SOLTERO',
        '2000-05-12',
        'Jorge Perez',
        '3884011111',
        'MASCULINO',
        '2024-01-10',
        'registrado'
    );
INSERT INTO education (name, address, education_level, person_id)
VALUES (
        'Escuela Belgrano',
        'address',
        'PRIMARIO',
        1
    ),
    (
        'Raul Galan',
        'Calle falsa',
        'PRIMARIO',
        2
    ),
    (
        'Comercio 4',
        'av. pte. peron',
        'Secundario',
        3
    );
INSERT INTO work (
        company_name,
        status,
        address,
        social_work,
        name_social_work,
        person_id
    )
VALUES (
        'Empresa Ledesma',
        'ACTIVO',
        'Av Siempre Viva 123',
        true,
        'OSDE',
        1
    ),
    (
        'Muni Ledesma',
        'ACTIVO',
        'Av Siempre Viva 123',
        true,
        'ISJ',
        2
    ),
    (
        'Ejesa',
        'ACTIVO',
        'Av libertad',
        true,
        'OSPAIL',
        3
    );
INSERT INTO health (
        active_cud,
        rehabilitation_treatment,
        cud_number,
        diagnostic,
        disability_type,
        person_id
    )
VALUES (true, true, 'CUD1001', 'MOTOR', 'FISICA', 1),
    (true, true, 'CUD1001', 'MOTOR', 'FISICA', 2),
    (true, true, 'CUD1001', 'MOTOR', 'FISICA', 3);
INSERT INTO address (street, district, locality, province, person_id)
VALUES (
        'Calle 1',
        'Barrio Centro',
        'Libertador',
        'Jujuy',
        1
    ),
    (
        'Calle 2',
        'Barrio Centro',
        'Libertador',
        'Jujuy',
        2
    ),
    (
        'Calle 3',
        'Barrio Centro',
        'Libertador',
        'Jujuy',
        3
    );
INSERT INTO benefit (
        pension,
        free_pass,
        federal_program,
        auh,
        merchandise,
        person_id
    )
VALUES (true, true, false, false, true, 1),
    (true, true, false, false, true, 2),
    (true, true, false, false, true, 3);
INSERT INTO family_member (
        first_name,
        last_name,
        dni,
        age,
        civil_status,
        parentage,
        occupation,
        person_id
    )
VALUES (
        'Maria',
        'Perez',
        '40111111',
        60,
        'CASADO',
        'MADRE',
        'AMA DE CASA',
        1
    ),
    (
        'Dante',
        'Gomez',
        '12345678',
        60,
        'CASADO',
        'PADRE',
        'EMPLEADO',
        2
    ),
    (
        'Lucas',
        'Fernandez',
        '40111111',
        60,
        'CASADO',
        'PRIMO',
        'Estudiante',
        3
    );