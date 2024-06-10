package org.iesvdm.api_rest.mapper;

import org.iesvdm.api_rest.domain.Invitation;
import org.iesvdm.api_rest.dto.InvitationDTO;

public class InvitationMapper {

    public InvitationDTO mapToDto(Invitation invitation) {

        InvitationDTO dto = new InvitationDTO();

        dto.setId(invitation.getId());
        dto.setEmail(invitation.getEmail());
        dto.setName(invitation.getName());
        dto.setEmail(invitation.getEmail());
        dto.setAccepted(invitation.getAccepted());
        dto.setAllergies(invitation.getAllergies());

        dto.setGuestId(invitation.getGuest().getId());
        dto.setWeddingId(invitation.getWedding().getId());

        return dto;
    }
}
