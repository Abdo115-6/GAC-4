package com.test.gac_4.services;

import com.test.gac_4.dto.UserLoginDTO;
import com.test.gac_4.dto.UserRegistrationDTO;
import com.test.gac_4.dto.AuthResponseDTO;
import com.test.gac_4.entities.users;
import com.test.gac_4.repositories.usersrepo;
import com.test.gac_4.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    private usersrepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthResponseDTO register(UserRegistrationDTO dto) {
        if (usersRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        users user = new users();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        user.setIsAdmin(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        users savedUser = usersRepo.save(user);
        String token = jwtProvider.generateToken(savedUser.getEmail());

        return new AuthResponseDTO(token, savedUser.getId(), savedUser.getEmail(),
                savedUser.getFirstName(), savedUser.getLastName(), savedUser.getIsAdmin());
    }

    public AuthResponseDTO login(UserLoginDTO dto) {
        users user = usersRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtProvider.generateToken(user.getEmail());

        return new AuthResponseDTO(token, user.getId(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getIsAdmin());
    }

    public users getUserByEmail(String email) {
        return usersRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
