package org.event.enaaskills;

import org.event.enaaskills.Entity.Competence;
import org.event.enaaskills.Entity.SousCompetence;
import org.event.enaaskills.Repository.CompetenceRepository;
import org.event.enaaskills.Service.CompetenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompetenceServiceTest {

    @Mock
    private CompetenceRepository competenceRepository;

    @InjectMocks
    private CompetenceService competenceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnTrue_WhenAllSousCompetencesValid() {
        Competence comp = new Competence();
        comp.setId(1L);
        comp.setNom("C");
        comp.setSousCompetences(Arrays.asList(
                new SousCompetence(1L, "A", true, null),
                new SousCompetence(2L, "B", true, null)
        ));

        when(competenceRepository.findById(1L)).thenReturn(Optional.of(comp));

        assertTrue(competenceService.isCompetenceAcquired(1L));
    }

    @Test
    public void shouldReturnFalse_WhenSomeSousCompetencesInvalid() {
        Competence comp = new Competence();
        comp.setId(1L);
        comp.setNom("C");
        comp.setSousCompetences(Arrays.asList(
                new SousCompetence(1L, "A", true, null),
                new SousCompetence(2L, "B", false, null)
        ));

        when(competenceRepository.findById(1L)).thenReturn(Optional.of(comp));

        assertFalse(competenceService.isCompetenceAcquired(1L));
    }

    @Test
    public void shouldReturnFalse_WhenCompetenceNotFound() {
        when(competenceRepository.findById(99L)).thenReturn(Optional.empty());

        assertFalse(competenceService.isCompetenceAcquired(99L));
    }
}
