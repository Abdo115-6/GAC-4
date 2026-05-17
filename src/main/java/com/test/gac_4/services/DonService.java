package com.test.gac_4.services;

import com.test.gac_4.dto.DonDTO;
import com.test.gac_4.entities.Don;
import com.test.gac_4.entities.PaymentStatus;
import com.test.gac_4.entities.ActionCharite;
import com.test.gac_4.entities.users;
import com.test.gac_4.repositories.DonRepo;
import com.test.gac_4.repositories.ActionChariteRepo;
import com.test.gac_4.repositories.usersrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonService {
    @Autowired
    private DonRepo donRepo;

    @Autowired
    private ActionChariteRepo actionRepo;

    @Autowired
    private usersrepo usersRepo;

    @Autowired
    private EmailService emailService;

    public DonDTO createDon(DonDTO dto) {
        Double montant = dto.getMontant() != null ? dto.getMontant() : dto.getAmount();
        if (montant == null || montant <= 0) {
            throw new RuntimeException("Valid amount is required");
        }

        Long actionId = dto.getActionId() != null ? dto.getActionId() : dto.getActionChariteId();
        if (actionId == null) {
            throw new RuntimeException("Action ID is required");
        }

        Long userId = dto.getUserId();
        if (userId == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
                String email = auth.getName();
                users user = usersRepo.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                userId = user.getId();
            } else {
                throw new RuntimeException("User authentication required");
            }
        }

        users user = usersRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ActionCharite action = actionRepo.findById(actionId)
                .orElseThrow(() -> new RuntimeException("Action not found"));

        Don don = new Don();
        don.setMontant(montant);
        don.setStatus(PaymentStatus.PENDING);
        don.setPaymentMethod(dto.getPaymentMethod() != null ? dto.getPaymentMethod() : "CASH");
        don.setNotes(dto.getNotes() != null ? dto.getNotes() : dto.getMessage());
        don.setUser(user);
        don.setAction(action);
        don.setCreatedAt(LocalDateTime.now());

        Don saved = donRepo.save(don);

        // Update action collected amount
        Double totalDonations = donRepo.sumDonationsByActionId(action.getId());
        if (totalDonations != null) {
            action.setMontantCollecte(totalDonations);
            actionRepo.save(action);
        }

        // Send confirmation email
        emailService.sendDonationConfirmationEmail(user.getEmail(), user.getFirstName(),
                don.getMontant(), action.getTitre());

        return mapToDTO(saved);
    }

    public DonDTO confirmDon(Long id) {
        Don don = donRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        don.setStatus(PaymentStatus.CONFIRMED);
        Don updated = donRepo.save(don);

        ActionCharite action = don.getAction();
        Double totalDonations = donRepo.sumDonationsByActionId(action.getId());
        if (totalDonations != null) {
            action.setMontantCollecte(totalDonations);
            actionRepo.save(action);
        }

        return mapToDTO(updated);
    }

    public DonDTO getDonById(Long id) {
        Don don = donRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        return mapToDTO(don);
    }

    public List<DonDTO> getDonationsByUser(Long userId) {
        return donRepo.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<DonDTO> getDonationsByAction(Long actionId) {
        return donRepo.findByActionId(actionId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Double getTotalDonationsByAction(Long actionId) {
        Double total = donRepo.sumDonationsByActionId(actionId);
        return total != null ? total : 0.0;
    }

    public List<DonDTO> getAllDonations() {
        return donRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DonDTO mapToDTO(Don don) {
        DonDTO dto = new DonDTO();
        dto.setId(don.getId());
        dto.setMontant(don.getMontant());
        dto.setStatus(don.getStatus().name());
        dto.setTransactionId(don.getTransactionId());
        dto.setPaymentMethod(don.getPaymentMethod());
        dto.setNotes(don.getNotes());
        dto.setUserId(don.getUser().getId());
        dto.setActionId(don.getAction().getId());
        dto.setUserName(don.getUser().getFirstName() + " " + don.getUser().getLastName());
        dto.setActionTitle(don.getAction().getTitre());
        dto.setOrganisationName(don.getAction().getOrganisation().getNom());
        dto.setCreatedAt(don.getCreatedAt() != null ? don.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
        return dto;
    }
}
