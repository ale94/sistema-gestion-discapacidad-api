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
        status,
        indicator_type
    )
VALUES (
        'lucas',
        'perez',
        '30111222',
        'soltero',
        '1990-05-10',
        'maria perez',
        '3884011111',
        'masculino',
        '2024-01-10',
        'registrado',
        'nada'
    ),
    (
        'ramon',
        'fernandez',
        '12111278',
        'casado',
        '1993-05-10',
        'gaston perez',
        '3884011111',
        'masculino',
        '2024-01-10',
        'registrado',
        'nada'
    ),
    (
        'juan',
        'gomez',
        '45114652',
        'soltero',
        '2000-05-12',
        'jorge perez',
        '3884011111',
        'masculino',
        '2024-01-10',
        'registrado',
        'nada'
    ),
       (
           'gaston',
           'pereyra',
           '15659456',
           'soltero',
           '2000-05-12',
           'jorge perez',
           '3884011111',
           'masculino',
           '2024-01-10',
           'en_seguimiento',
           'Grado de Discapacidad: Niveles de 0 a 4 (desde sin discapacidad h.'
       ),
       (
           'franco',
           'torres',
           '45789456',
           'soltero',
           '2000-05-12',
           'jorge perez',
           '3884011111',
           'masculino',
           '2024-01-10',
           'en_seguimiento',
           'Grado de Dependencia: Medición de la autonomía en actividades de la.'
       );
INSERT INTO education (name, address, education_level, person_id)
VALUES (
        'escuela belgrano',
        'address',
        'primario',
        1
    ),
    (
        'raul galan',
        'calle falsa',
        'primario',
        2
    ),
    (
        'comercio 4',
        'av. pte. peron',
        'secundario',
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
        'empresa ledesma',
        'activo',
        'av siempre viva 123',
        true,
        'osde',
        1
    ),
    (
        'muni ledesma',
        'activo',
        'av siempre viva 123',
        true,
        'isj',
        2
    ),
    (
        'ejesa',
        'activo',
        'av libertad',
        true,
        'ospail',
        3
    ),
(
    'work2',
    'activo',
    'av 12312',
    true,
    'adasd',
    4
),
(
        'loss',
        'activo',
        'av sadsad',
        true,
        'dasdsa',
        5
    );
INSERT INTO health (
        active_cud,
        rehabilitation_treatment,
        cud_number,
        diagnostic,
        disability_type,
        person_id
    )
VALUES (true, true, 'cud1001', 'motor', 'fisica', 1),
    (true, true, 'cud1001', 'motor', 'fisica', 2),
    (true, true, 'cud1001', 'motor', 'fisica', 3);
INSERT INTO address (street, district, locality, province, person_id)
VALUES (
        'calle 1',
        'barrio centro',
        'libertador',
        'jujuy',
        1
    ),
    (
        'calle 2',
        'barrio centro',
        'libertador',
        'jujuy',
        2
    ),
    (
        'calle 3',
        'barrio centro',
        'libertador',
        'jujuy',
        3
    ),
       (
           'calle 4',
           'barrio centro',
           'libertador',
           'jujuy',
           4
       ),
       (
           'calle 5',
           'barrio centro',
           'libertador',
           'jujuy',
           5
       )
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
        'maria',
        'perez',
        '40111111',
        60,
        'casado',
        'madre',
        'ama de casa',
        1
    ),
    (
        'dante',
        'gomez',
        '12345678',
        60,
        'casado',
        'padre',
        'empleado',
        2
    ),
    (
        'lucas',
        'fernandez',
        '40111111',
        60,
        'casado',
        'primo',
        'estudiante',
        3
    );