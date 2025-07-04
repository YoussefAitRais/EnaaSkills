package org.event.enaaskills.Service;

import org.event.enaaskills.DTO.CompetenceDTO;
import org.event.enaaskills.DTO.CompetenceStatusDTO;
import org.event.enaaskills.Entity.Competence;
import org.event.enaaskills.Entity.SousCompetence;
import org.event.enaaskills.Mapper.CompetenceMapper;
import org.event.enaaskills.Repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public Competence createCompetenceFromDTO(CompetenceDTO dto) {
        Competence competence = CompetenceMapper.fromDTO(dto);
        return competenceRepository.save(competence);
    }

    public Competence updateCompetenceFromDTO(Long id, CompetenceDTO dto) {
        return competenceRepository.findById(id).map(competence -> {
            competence.setNom(dto.getNom());
            return competenceRepository.save(competence);
        }).orElseThrow(() -> new RuntimeException("Competence not found"));
    }

    public Optional<Competence> getCompetenceById(Long id) {
        return competenceRepository.findById(id);
    }

    public void deleteCompetence(Long id) {
        competenceRepository.deleteById(id);
    }

    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    public boolean isCompetenceAcquired(Long id) {
        return competenceRepository.findById(id).map(c -> {
            List<SousCompetence> sous = c.getSousCompetences();
            return sous != null && !sous.isEmpty() && sous.stream().allMatch(SousCompetence::isValidee);
        }).orElse(false);
    }

    public List<CompetenceDTO> getAllCompetencesDTO() {
        return competenceRepository.findAll()
                .stream()
                .map(CompetenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CompetenceStatusDTO> getCompetenceDashboard() {
        return competenceRepository.findAll()
                .stream()
                .map(CompetenceMapper::toStatusDTO)
                .collect(Collectors.toList());
    }



}
