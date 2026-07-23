package ar.com.ale.sistema_discapacidad_api.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EducationStatus {
    COMPLETO,
    INCOMPLETO;
    @JsonCreator
    public static EducationStatus fromString(String value) {
        if (value == null || value.isBlank()) {
            return null; // Si viene "", " " o null, lo convierte en null
        }
        return EducationStatus.valueOf(value.toUpperCase());
    }
}