package org.iesvdm.api_rest.mapper;

import org.iesvdm.api_rest.domain.Guest;
import org.iesvdm.api_rest.domain.Invitation;
import org.iesvdm.api_rest.domain.Wedding;
import org.iesvdm.api_rest.dto.InvitationDTO;
import org.iesvdm.api_rest.repository.InvitationRepository;
import org.iesvdm.api_rest.service.GuestService;
import org.iesvdm.api_rest.service.WeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvitationMapper {

    @Autowired
    InvitationRepository invitationRepository;
    @Autowired
    private GuestService guestService;
    @Autowired
    private WeddingService weddingService;

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

    public Invitation mapToInvitation(InvitationDTO invitationDto) {

        Invitation invitation = new Invitation();

        invitation.setId(invitationDto.getId());
        invitation.setEmail(invitationDto.getEmail());
        invitation.setName(invitationDto.getName());
        invitation.setEmail(invitationDto.getEmail());
        invitation.setAccepted(invitationDto.getAccepted());
        invitation.setAllergies(invitationDto.getAllergies());

//        if(invitationDto.getGuestId()!=null){
//            Guest guest = guestService.one(invitationDto.getGuestId());
//            invitation.setGuest(guest);
//        }
//
//        if(invitationDto.getWeddingId()!=null){
//            Wedding wedding = weddingService.one(invitationDto.getWeddingId());
//            invitation.setWedding(wedding);
//        }

        return invitation;
    }
}
