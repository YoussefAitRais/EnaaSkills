package org.event.enaaskills.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SousCompetenceDTO {
    private Long id;
    private String nom;
    private boolean validee;
    private Long competenceId; // باش نعرف شنو هي compétence المرتبطة
}
