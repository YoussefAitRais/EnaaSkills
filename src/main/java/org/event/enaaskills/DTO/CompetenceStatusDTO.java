package org.event.enaaskills.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompetenceStatusDTO {
    private Long id;
    private String nom;
    private boolean acquired;
}
