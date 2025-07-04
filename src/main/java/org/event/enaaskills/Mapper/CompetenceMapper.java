package org.event.enaaskills.Mapper;

import org.event.enaaskills.DTO.CompetenceDTO;
import org.event.enaaskills.DTO.CompetenceStatusDTO;
import org.event.enaaskills.Entity.Competence;
import org.event.enaaskills.Entity.SousCompetence;

import java.util.List;

public class CompetenceMapper {

    public static CompetenceDTO toDTO(Competence competence) {
        return new CompetenceDTO(
                competence.getId(),
                competence.getNom()
        );
    }

    public static CompetenceStatusDTO toStatusDTO(Competence competence) {
        boolean acquired = false;

        List<SousCompetence> sous = competence.getSousCompetences();
        if (sous != null && !sous.isEmpty()) {
            acquired = sous.stream().allMatch(SousCompetence::isValidee);
        }

        return new CompetenceStatusDTO(
                competence.getId(),
                competence.getNom(),
                acquired
        );
    }

    public static Competence fromDTO(CompetenceDTO dto) {
        Competence competence = new Competence();
        competence.setId(dto.getId());
        competence.setNom(dto.getNom());
        return competence;
    }
}
