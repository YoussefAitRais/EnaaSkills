package org.event.enaaskills.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetenceDTO {
    private Long id;
    private String nom;

    private List<SousCompetenceDTO> sousCompetences;

    public CompetenceDTO(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

}
