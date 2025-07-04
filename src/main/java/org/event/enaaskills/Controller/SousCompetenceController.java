package org.event.enaaskills.Controller;

import org.event.enaaskills.DTO.SousCompetenceDTO;
import org.event.enaaskills.Service.SousCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sous-competences")
@CrossOrigin
public class SousCompetenceController {

    @Autowired
    private SousCompetenceService sousCompetenceService;

    @PostMapping
    public ResponseEntity<SousCompetenceDTO> create(@RequestBody SousCompetenceDTO dto) {
        return ResponseEntity.ok(sousCompetenceService.createSousCompetence(dto));
    }

    @GetMapping
    public ResponseEntity<List<SousCompetenceDTO>> getAll() {
        return ResponseEntity.ok(sousCompetenceService.getAllSousCompetences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SousCompetenceDTO> getById(@PathVariable Long id) {
        return sousCompetenceService.getSousCompetenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-competence/{competenceId}")
    public ResponseEntity<List<SousCompetenceDTO>> getByCompetence(@PathVariable Long competenceId) {
        return ResponseEntity.ok(sousCompetenceService.getByCompetenceId(competenceId));
    }

    @PutMapping("/{id}/validation")
    public ResponseEntity<SousCompetenceDTO> updateValidation(
            @PathVariable Long id,
            @RequestParam boolean status) {
        return ResponseEntity.ok(sousCompetenceService.updateValidationStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sousCompetenceService.deleteSousCompetence(id);
        return ResponseEntity.noContent().build();
    }
}
