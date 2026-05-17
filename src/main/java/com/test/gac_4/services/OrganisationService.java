package com.test.gac_4.services;

import com.test.gac_4.dto.OrganisationDTO;
import com.test.gac_4.entities.Organisation;
import com.test.gac_4.entities.users;
import com.test.gac_4.repositories.OrganisationRepo;
import com.test.gac_4.repositories.usersrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganisationService {
    @Autowired
    private OrganisationRepo organisationRepo;

    @Autowired
    private usersrepo usersRepo;

    @Autowired
    private EmailService emailService;

    public OrganisationDTO createOrganisation(OrganisationDTO dto) {
        if (organisationRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Organization email already exists");
        }

        Organisation org = new Organisation();
        org.setNom(dto.getNom());
        org.setAdresse(dto.getAdresse());
        org.setEmail(dto.getEmail());
        org.setPhone(dto.getPhone());
        org.setTaxId(dto.getTaxId());
        org.setDescription(dto.getDescription());
        org.setWebsite(dto.getWebsite());
        org.setValide(false);
        org.setCreatedAt(LocalDateTime.now());
        org.setUpdatedAt(LocalDateTime.now());

        if (dto.getAdminId() != null) {
            users admin = usersRepo.findById(dto.getAdminId())
                    .orElseThrow(() -> new RuntimeException("Admin user not found"));
            org.setAdmin(admin);
        }

        Organisation saved = organisationRepo.save(org);
        return mapToDTO(saved);
    }

    public OrganisationDTO getOrganisationById(Long id) {
        Organisation org = organisationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        return mapToDTO(org);
    }

    public OrganisationDTO updateOrganisation(Long id, OrganisationDTO dto) {
        Organisation org = organisationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        org.setNom(dto.getNom());
        org.setAdresse(dto.getAdresse());
        org.setPhone(dto.getPhone());
        org.setDescription(dto.getDescription());
        org.setWebsite(dto.getWebsite());
        org.setUpdatedAt(LocalDateTime.now());

        Organisation updated = organisationRepo.save(org);
        return mapToDTO(updated);
    }

    public void validateOrganisation(Long id) {
        Organisation org = organisationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        org.setValide(true);
        org.setUpdatedAt(LocalDateTime.now());
        organisationRepo.save(org);
        
        // Send validation email
        emailService.sendOrganisationValidationEmail(org.getEmail(), org.getNom());
    }

    public void rejectOrganisation(Long id) {
        Organisation org = organisationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        org.setIsActive(false);
        organisationRepo.save(org);
        
        emailService.sendOrganisationRejectionEmail(org.getEmail(), org.getNom());
    }

    public void deleteOrganisation(Long id) {
        Organisation org = organisationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        organisationRepo.delete(org);
    }

    public List<OrganisationDTO> getAllPendingOrganisations() {
        return organisationRepo.findByValideFalse()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrganisationDTO> getAllValidatedOrganisations() {
        return organisationRepo.findByValideTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrganisationDTO> getAllOrganisations() {
        return organisationRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private OrganisationDTO mapToDTO(Organisation org) {
        OrganisationDTO dto = new OrganisationDTO();
        dto.setId(org.getId());
        dto.setNom(org.getNom());
        dto.setAdresse(org.getAdresse());
        dto.setEmail(org.getEmail());
        dto.setPhone(org.getPhone());
        dto.setTaxId(org.getTaxId());
        dto.setLogo(org.getLogo());
        dto.setDescription(org.getDescription());
        dto.setWebsite(org.getWebsite());
        dto.setValide(org.getValide());
        dto.setIsActive(org.getIsActive());
        if (org.getAdmin() != null) {
            dto.setAdminId(org.getAdmin().getId());
        }
        return dto;
    }
}
