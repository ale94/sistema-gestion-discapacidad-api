
-- =========================
-- PERSON
-- =========================
CREATE TABLE person (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    dni VARCHAR(20) UNIQUE,
    civil_status VARCHAR(50),
    date_birth DATE,
    tutor VARCHAR(100),
    phone VARCHAR(100),
    gender VARCHAR(100),
    registration_date DATE
);
-- =========================
-- EDUCATION
-- =========================
CREATE TABLE education (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200),
    address VARCHAR(200),
    education_level VARCHAR(100),
    person_id BIGINT UNIQUE,
    CONSTRAINT fk_education_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);
-- =========================
-- WORK
-- =========================
CREATE TABLE work (
    id BIGSERIAL PRIMARY KEY,
    company_name VARCHAR(200),
    status VARCHAR(200),
    address VARCHAR(200),
    social_work BOOLEAN,
    name_social_work VARCHAR(200),
    person_id BIGINT UNIQUE,
    CONSTRAINT fk_work_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);
-- =========================
-- HEALTH
-- =========================
CREATE TABLE health (
    id BIGSERIAL PRIMARY KEY,
    cud_number VARCHAR(50),
    active_cud BOOLEAN,
    rehabilitation_treatment BOOLEAN,
    diagnostic VARCHAR(50),
    disability_type VARCHAR(50),
    person_id BIGINT UNIQUE,
    CONSTRAINT fk_health_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);
-- =========================
-- ADDRESS
-- =========================
CREATE TABLE address (
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(200),
    district VARCHAR(100),
    locality VARCHAR(100),
    province VARCHAR(100),
    person_id BIGINT UNIQUE,
    CONSTRAINT fk_address_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);
-- =========================
-- BENEFIT
-- =========================
CREATE TABLE benefit (
    id BIGSERIAL PRIMARY KEY,
    pension BOOLEAN,
    free_pass BOOLEAN,
    federal_program BOOLEAN,
    auh BOOLEAN,
    merchandise BOOLEAN,
    person_id BIGINT UNIQUE,
    CONSTRAINT fk_benefit_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE family_member (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    dni VARCHAR(50),
    age INTEGER,
    civil_status VARCHAR(100),
    parentage VARCHAR(100),
    occupation VARCHAR(100),

    person_id BIGINT,
    CONSTRAINT fk_family_member_person
        FOREIGN KEY (person_id)
        REFERENCES person(id)
        ON DELETE CASCADE
);
CREATE TABLE person_tracking (
    id BIGSERIAL PRIMARY KEY,
    last_name VARCHAR(100),
    first_name VARCHAR(100),
    dni VARCHAR(20) UNIQUE,
    indicator_type VARCHAR(200),
    address VARCHAR(100),
    phone VARCHAR(100)
);