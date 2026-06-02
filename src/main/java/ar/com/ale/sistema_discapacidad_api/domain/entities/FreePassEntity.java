package ar.com.ale.sistema_discapacidad_api.domain.entities;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;

@Entity(name = "free_pass")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreePassEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FreePassType type;

    private LocalDate startDate;

    private String reason;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @Builder.Default
    @OneToMany(
            mappedBy = "freePass",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FreePassRenewalEntity> renewals = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FreePassStatus status;
}
