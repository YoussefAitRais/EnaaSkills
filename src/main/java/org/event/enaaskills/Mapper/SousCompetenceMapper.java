package org.event.enaaskills.Mapper;

import org.event.enaaskills.DTO.SousCompetenceDTO;
import org.event.enaaskills.Entity.Competence;
import org.event.enaaskills.Entity.SousCompetence;

public class SousCompetenceMapper {

    public static SousCompetenceDTO toDTO(SousCompetence sc) {
        return new SousCompetenceDTO(
                sc.getId(),
                sc.getNom(),
                sc.isValidee(),
                sc.getCompetence() != null ? sc.getCompetence().getId() : null
        );
    }

    public static SousCompetence fromDTO(SousCompetenceDTO dto, Competence competence) {
        SousCompetence sc = new SousCompetence();
        sc.setId(dto.getId());
        sc.setNom(dto.getNom());
        sc.setValidee(dto.isValidee());
        sc.setCompetence(competence);
        return sc;
    }
}
