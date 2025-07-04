package org.event.enaaskills.Controller;

import org.event.enaaskills.DTO.CompetenceDTO;
import org.event.enaaskills.DTO.CompetenceStatusDTO;
import org.event.enaaskills.Entity.Competence;
import org.event.enaaskills.Mapper.CompetenceMapper;
import org.event.enaaskills.Service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/competences")
@CrossOrigin
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @PostMapping
    public ResponseEntity<CompetenceDTO> createCompetence(@RequestBody CompetenceDTO dto) {
        Competence created = competenceService.createCompetenceFromDTO(dto);
        return ResponseEntity.ok(CompetenceMapper.toDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<CompetenceDTO>> getAllCompetences() {
        List<CompetenceDTO> competences = competenceService.getAllCompetences()
                .stream()
                .map(CompetenceMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(competences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenceDTO> getCompetenceById(@PathVariable Long id) {
        Optional<Competence> opt = competenceService.getCompetenceById(id);
        return opt.map(c -> ResponseEntity.ok(CompetenceMapper.toDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Modifier une compétence
    @PutMapping("/{id}")
    public ResponseEntity<CompetenceDTO> updateCompetence(@PathVariable Long id, @RequestBody CompetenceDTO dto) {
        Competence updated = competenceService.updateCompetenceFromDTO(id, dto);
        return ResponseEntity.ok(CompetenceMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/acquired")
    public ResponseEntity<Boolean> isCompetenceAcquired(@PathVariable Long id) {
        return ResponseEntity.ok(competenceService.isCompetenceAcquired(id));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<CompetenceStatusDTO>> getDashboard() {
        return ResponseEntity.ok(competenceService.getCompetenceDashboard());
    }


}
