package com.test.gac_4.services;

import com.test.gac_4.dto.UserResponseDTO;
import com.test.gac_4.entities.users;
import com.test.gac_4.repositories.usersrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private usersrepo usersRepo;

    public UserResponseDTO getUserById(Long id) {
        users user = usersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    public UserResponseDTO getUserByEmail(String email) {
        users user = usersRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserResponseDTO dto) {
        users user = usersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        user.setAvatar(dto.getAvatar());
        user.setBio(dto.getBio());
        user.setUpdatedAt(LocalDateTime.now());

        users updated = usersRepo.save(user);
        return mapToDTO(updated);
    }

    public void deleteUser(Long id) {
        users user = usersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        usersRepo.save(user);
    }

    public List<UserResponseDTO> getAllAdmins() {
        return usersRepo.findByIsAdmin(true)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserResponseDTO mapToDTO(users user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setAvatar(user.getAvatar());
        dto.setBio(user.getBio());
        dto.setIsAdmin(user.getIsAdmin());
        dto.setIsActive(user.getIsActive());
        return dto;
    }
}
