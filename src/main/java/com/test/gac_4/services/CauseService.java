package com.test.gac_4.services;

import com.test.gac_4.dto.CauseDTO;
import com.test.gac_4.entities.Cause;
import com.test.gac_4.repositories.CauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CauseService {

    @Autowired
    private CauseRepository causeRepository;

    public List<CauseDTO> getAllCauses() {
        return causeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CauseDTO addCause(CauseDTO causeDTO) {
        Cause cause = new Cause();
        cause.setName(causeDTO.getName());
        cause.setDescription(causeDTO.getDescription());
        causeRepository.save(cause);
        return mapToDTO(cause);
    }

    public CauseDTO getCauseById(Long id) {
        Cause cause = causeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cause not found"));
        return mapToDTO(cause);
    }

    public CauseDTO updateCause(Long id, CauseDTO causeDTO) {
        Cause cause = causeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cause not found"));
        cause.setName(causeDTO.getName());
        cause.setDescription(causeDTO.getDescription());
        causeRepository.save(cause);
        return mapToDTO(cause);
    }

    public void deleteCause(Long id) {
        Cause cause = causeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cause not found"));
        causeRepository.delete(cause);
    }

    private CauseDTO mapToDTO(Cause cause) {
        CauseDTO dto = new CauseDTO();
        dto.setId(cause.getId());
        dto.setName(cause.getName());
        dto.setDescription(cause.getDescription());
        return dto;
    }
}
