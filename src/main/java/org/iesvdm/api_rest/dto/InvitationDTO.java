package org.iesvdm.api_rest.dto;

import lombok.*;
import org.iesvdm.api_rest.domain.Invitation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvitationDTO {

    @EqualsAndHashCode.Include
    private long id;
    private String name;
    private String email;
    private Boolean accepted;
    private String allergies;
    private Long weddingId;
    private Long guestId;

}
