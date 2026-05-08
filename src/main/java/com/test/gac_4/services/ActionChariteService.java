package com.test.gac_4.services;

import com.test.gac_4.dto.ActionChariteDTO;
import com.test.gac_4.dto.StatsDTO;
import com.test.gac_4.entities.ActionCharite;
import com.test.gac_4.entities.CategoryEnum;
import com.test.gac_4.entities.Organisation;
import com.test.gac_4.repositories.ActionChariteRepo;
import com.test.gac_4.repositories.OrganisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionChariteService {
    @Autowired
    private ActionChariteRepo actionRepo;

    @Autowired
    private OrganisationRepo organisationRepo;

    public ActionChariteDTO createAction(ActionChariteDTO dto) {
        Organisation org = organisationRepo.findById(dto.getOrganisationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        ActionCharite action = new ActionCharite();
        action.setTitre(dto.getTitre());
        action.setDescription(dto.getDescription());
        action.setCategory(CategoryEnum.valueOf(dto.getCategory()));
        action.setDateAction(dto.getDateAction());
        action.setLieu(dto.getLieu());
        action.setObjectifMontant(dto.getObjectifMontant());
        action.setMontantCollecte(0.0);
        action.setOrganisation(org);
        action.setCreatedAt(LocalDateTime.now());
        action.setUpdatedAt(LocalDateTime.now());

        ActionCharite saved = actionRepo.save(action);
        return mapToDTO(saved);
    }

    public ActionChariteDTO getActionById(Long id) {
        ActionCharite action = actionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));
        return mapToDTO(action);
    }

    public ActionChariteDTO updateAction(Long id, ActionChariteDTO dto) {
        ActionCharite action = actionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));

        action.setTitre(dto.getTitre());
        action.setDescription(dto.getDescription());
        action.setLieu(dto.getLieu());
        action.setObjectifMontant(dto.getObjectifMontant());
        action.setUpdatedAt(LocalDateTime.now());

        ActionCharite updated = actionRepo.save(action);
        return mapToDTO(updated);
    }

    public void archiveAction(Long id) {
        ActionCharite action = actionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));
        action.setArchived(true);
        action.setUpdatedAt(LocalDateTime.now());
        actionRepo.save(action);
    }

    public List<ActionChariteDTO> getActionsByOrganisation(Long organisationId) {
        return actionRepo.findByOrganisationIdAndArchivedFalse(organisationId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ActionChariteDTO> getActionsByCategory(String category) {
        return actionRepo.findByCategory(category)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ActionChariteDTO> getAllActiveActions() {
        return actionRepo.findByArchivedFalse()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ActionChariteDTO> getPopularActions() {
        return actionRepo.findByArchivedFalse()
                .stream()
                .sorted((a1, a2) -> Double.compare(a2.getMontantCollecte(), a1.getMontantCollecte()))
                .limit(10)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StatsDTO getPublicStats() {
        long totalActions = actionRepo.count();
        // For now, return basic stats. In a real app, you'd query users and donations tables
        return new StatsDTO(totalActions, 0, 0.0, 0);
    }

    private ActionChariteDTO mapToDTO(ActionCharite action) {
        ActionChariteDTO dto = new ActionChariteDTO();
        dto.setId(action.getId());
        dto.setTitre(action.getTitre());
        dto.setDescription(action.getDescription());
        dto.setCategory(action.getCategory().name());
        dto.setDateAction(action.getDateAction());
        dto.setLieu(action.getLieu());
        dto.setObjectifMontant(action.getObjectifMontant());
        dto.setMontantCollecte(action.getMontantCollecte());
        dto.setImage(action.getImage());
        dto.setVideo(action.getVideo());
        dto.setArchived(action.getArchived());
        dto.setIsActive(action.getIsActive());
        dto.setOrganisationId(action.getOrganisation().getId());
        dto.setParticipantCount(action.getParticipantCount());
        dto.setProgressPercentage(action.getProgressPercentage());
        return dto;
    }
}
