package com.test.gac_4.security;

import com.test.gac_4.entities.users;
import com.test.gac_4.repositories.usersrepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private usersrepo userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");

        users user = userRepository.findByEmail(email).orElseGet(() -> {
            users newUser = new users();
            newUser.setEmail(email);
            newUser.setPassword("{oauth2}"); 
            newUser.setFirstName(name != null ? name.split(" ")[0] : "");
            newUser.setLastName(name != null && name.contains(" ") ? name.substring(name.indexOf(" ") + 1) : "");
            newUser.setAvatar(picture);
            newUser.setIsAdmin(false);
            newUser.setIsActive(true);
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
        });

        if (!user.getIsActive()) {
            response.sendRedirect("/login?error=disabled");
            return;
        }

        String token = jwtProvider.generateToken(email);
        String frontendUrl = "http://localhost:4200";
        // Store token in session to avoid large header size error
        request.getSession().setAttribute("oauth2_token", token);
        response.sendRedirect(frontendUrl + "/oauth2-callback");
    }
}