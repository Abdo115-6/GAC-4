package com.test.gac_4.controller;

import com.test.gac_4.dto.UserRegistrationDTO;
import com.test.gac_4.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WebController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/marketplace")
    public String marketplace() {
        return "marketplace";
    }

    @GetMapping("/organisations")
    public String organisations() {
        return "organisations";
    }

    @PostMapping("/register")
    public String registerWeb(@Valid @ModelAttribute UserRegistrationDTO dto) {
        try {
            authService.register(dto);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            return "redirect:/register?error=" + e.getMessage();
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Clear the session
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
