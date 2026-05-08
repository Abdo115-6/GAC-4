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
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
        users user = usersRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ActionCharite action = actionRepo.findById(dto.getActionId())
                .orElseThrow(() -> new RuntimeException("Action not found"));

        Don don = new Don();
        don.setMontant(dto.getMontant());
        don.setStatus(PaymentStatus.PENDING);
        don.setPaymentMethod(dto.getPaymentMethod());
        don.setNotes(dto.getNotes());
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

        // Update action collected amount
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
        return dto;
    }
}
