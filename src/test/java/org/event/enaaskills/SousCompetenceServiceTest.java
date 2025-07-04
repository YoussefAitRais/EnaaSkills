package org.event.enaaskills;

import org.event.enaaskills.DTO.SousCompetenceDTO;
import org.event.enaaskills.Entity.SousCompetence;
import org.event.enaaskills.Repository.SousCompetenceRepository;
import org.event.enaaskills.Service.SousCompetenceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SousCompetenceServiceTest {

    @Mock
    private SousCompetenceRepository sousCompetenceRepository;

    @InjectMocks
    private SousCompetenceService sousCompetenceService;

    public SousCompetenceServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSousCompetenceById_ShouldReturnSousCompetence() {
        SousCompetence sc = new SousCompetence(1L, "SC1", false, null);
        when(sousCompetenceRepository.findById(1L)).thenReturn(Optional.of(sc));

        Optional<SousCompetenceDTO> result = sousCompetenceService.getSousCompetenceById(1L);

        assertTrue(result.isPresent());
        assertEquals("SC1", result.get().getNom());
    }

    @Test
    public void testUpdateValidationStatus_ShouldUpdateSuccessfully() {
        SousCompetence sc = new SousCompetence(1L, "SC1", false, null);
        when(sousCompetenceRepository.findById(1L)).thenReturn(Optional.of(sc));
        when(sousCompetenceRepository.save(any(SousCompetence.class))).thenReturn(sc);

        SousCompetenceDTO updated = sousCompetenceService.updateValidationStatus(1L, true);

        assertTrue(updated.isValidee());
        verify(sousCompetenceRepository, times(1)).save(sc);
    }

    @Test
    public void testDeleteSousCompetence_ShouldCallRepositoryDelete() {
        doNothing().when(sousCompetenceRepository).deleteById(1L);

        sousCompetenceService.deleteSousCompetence(1L);

        verify(sousCompetenceRepository, times(1)).deleteById(1L);
    }
}
