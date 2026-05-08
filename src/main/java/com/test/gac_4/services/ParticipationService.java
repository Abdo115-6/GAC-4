package com.test.gac_4.services;

import com.test.gac_4.dto.ParticipationDTO;
import com.test.gac_4.entities.Participation;
import com.test.gac_4.entities.ActionCharite;
import com.test.gac_4.entities.users;
import com.test.gac_4.repositories.ParticipationRepo;
import com.test.gac_4.repositories.ActionChariteRepo;
import com.test.gac_4.repositories.usersrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipationService {
    @Autowired
    private ParticipationRepo participationRepo;

    @Autowired
    private ActionChariteRepo actionRepo;

    @Autowired
    private usersrepo usersRepo;

    public ParticipationDTO joinAction(ParticipationDTO dto) {
        users user = usersRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ActionCharite action = actionRepo.findById(dto.getActionId())
                .orElseThrow(() -> new RuntimeException("Action not found"));

        // Check if user already participates
        if (participationRepo.findByUserIdAndActionId(dto.getUserId(), dto.getActionId()).isPresent()) {
            throw new RuntimeException("User already participates in this action");
        }

        Participation participation = new Participation();
        participation.setUser(user);
        participation.setAction(action);
        participation.setNotes(dto.getNotes());
        participation.setCreatedAt(LocalDateTime.now());

        Participation saved = participationRepo.save(participation);
        return mapToDTO(saved);
    }

    public void leaveAction(Long participationId) {
        Participation participation = participationRepo.findById(participationId)
                .orElseThrow(() -> new RuntimeException("Participation not found"));
        participation.setIsActive(false);
        participationRepo.save(participation);
    }

    public ParticipationDTO getParticipation(Long id) {
        Participation participation = participationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Participation not found"));
        return mapToDTO(participation);
    }

    public List<ParticipationDTO> getParticipationsByUser(Long userId) {
        return participationRepo.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ParticipationDTO> getParticipationsByAction(Long actionId) {
        return participationRepo.findByActionId(actionId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Long getActionParticipantCount(Long actionId) {
        return participationRepo.countByActionId(actionId);
    }

    private ParticipationDTO mapToDTO(Participation participation) {
        ParticipationDTO dto = new ParticipationDTO();
        dto.setId(participation.getId());
        dto.setNotes(participation.getNotes());
        dto.setIsActive(participation.getIsActive());
        dto.setUserId(participation.getUser().getId());
        dto.setActionId(participation.getAction().getId());
        return dto;
    }
}