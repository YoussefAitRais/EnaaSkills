package org.event.enaaskills.Service;

import org.event.enaaskills.DTO.SousCompetenceDTO;
import org.event.enaaskills.Entity.Competence;
import org.event.enaaskills.Entity.SousCompetence;
import org.event.enaaskills.Mapper.SousCompetenceMapper;
import org.event.enaaskills.Repository.CompetenceRepository;
import org.event.enaaskills.Repository.SousCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SousCompetenceService {

    @Autowired
    private SousCompetenceRepository sousCompetenceRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    public SousCompetenceDTO createSousCompetence(SousCompetenceDTO dto) {
        Competence competence = competenceRepository.findById(dto.getCompetenceId())
                .orElseThrow(() -> new RuntimeException("Competence not found"));

        SousCompetence sc = SousCompetenceMapper.fromDTO(dto, competence);
        return SousCompetenceMapper.toDTO(sousCompetenceRepository.save(sc));
    }

    public List<SousCompetenceDTO> getAllSousCompetences() {
        return sousCompetenceRepository.findAll()
                .stream()
                .map(SousCompetenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SousCompetenceDTO> getSousCompetenceById(Long id) {
        return sousCompetenceRepository.findById(id)
                .map(SousCompetenceMapper::toDTO);
    }

    public List<SousCompetenceDTO> getByCompetenceId(Long competenceId) {
        return sousCompetenceRepository.findByCompetenceId(competenceId)
                .stream()
                .map(SousCompetenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SousCompetenceDTO updateValidationStatus(Long id, boolean status) {
        return sousCompetenceRepository.findById(id).map(sc -> {
            sc.setValidee(status);
            return SousCompetenceMapper.toDTO(sousCompetenceRepository.save(sc));
        }).orElseThrow(() -> new RuntimeException("Sous-compétence not found"));
    }

    public void deleteSousCompetence(Long id) {
        sousCompetenceRepository.deleteById(id);
    }
}
