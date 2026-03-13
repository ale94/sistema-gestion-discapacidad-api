package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest implements Serializable {

    private String name;
    private String type;
    private LocalDate date;
    private String description;
    private Integer attendees;

}
