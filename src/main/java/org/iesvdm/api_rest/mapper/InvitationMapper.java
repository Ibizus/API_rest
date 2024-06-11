package org.iesvdm.api_rest.mapper;

import org.iesvdm.api_rest.domain.Invitation;
import org.iesvdm.api_rest.dto.InvitationDTO;
import org.iesvdm.api_rest.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvitationMapper {

    @Autowired
    InvitationRepository invitationRepository;

    public InvitationDTO mapToDto(Invitation invitation) {

        InvitationDTO dto = new InvitationDTO();

        dto.setId(invitation.getId());
        dto.setEmail(invitation.getEmail());
        dto.setName(invitation.getName());
        dto.setEmail(invitation.getEmail());
        dto.setAccepted(invitation.getAccepted());
        dto.setAllergies(invitation.getAllergies());

        if(invitation.getGuest()!=null){
            dto.setGuestId(invitation.getGuest().getId());
        }

        dto.setWeddingId(invitationRepository.findWeddingIdByInvitationId(invitation.getId()).get());

        return dto;
    }
}
