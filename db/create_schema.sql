-- =========================
-- PERSON
-- =========================
CREATE TABLE person (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    dni BIGSERIAL UNIQUE,
    civil_status VARCHAR(50),
    date_birth DATE,
    tutor VARCHAR(100),
    phone BIGSERIAL,
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
-- =========================
-- FAMILY MEMBER
-- =========================
CREATE TABLE family_member (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    dni BIGSERIAL,
    age INTEGER,
    civil_status VARCHAR(100),
    parentage VARCHAR(100),
    occupation VARCHAR(100),
    person_id BIGINT,
    CONSTRAINT fk_family_member_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);
-- =========================
-- PERSON TRACKING
-- =========================
CREATE TABLE person_tracking (
    id BIGSERIAL PRIMARY KEY,
    last_name VARCHAR(100),
    first_name VARCHAR(100),
    dni BIGSERIAL UNIQUE,
    indicator_type VARCHAR(200),
    address VARCHAR(100),
    phone BIGSERIAL
);
-- =========================
-- EVENT
-- =========================
CREATE TABLE event (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    date DATE,
    description TEXT,
    attendees INTEGER
);
-- =========================
-- EQUIPMENT TYPE
-- =========================
CREATE TABLE equipment_type (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);
-- =========================
-- EQUIPMENT
-- =========================
CREATE TABLE equipment (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    total_stock INTEGER NOT NULL,
    status VARCHAR(50),
    created_at DATE,
    equipment_type_id BIGINT,
    CONSTRAINT fk_equipment_type FOREIGN KEY (equipment_type_id) REFERENCES equipment_type(id)
);
-- =========================
-- LOAN
-- =========================
CREATE TABLE loan (
                      id BIGSERIAL PRIMARY KEY,
                      type VARCHAR(100),
                      equipment_number VARCHAR(100),
                      dni BIGSERIAL,
                      applicant VARCHAR(150),
                      address VARCHAR(200),
                      phone BIGSERIAL,
                      year VARCHAR(4),
                      request_date DATE,
                      expiration DATE,
                      return_date DATE
);