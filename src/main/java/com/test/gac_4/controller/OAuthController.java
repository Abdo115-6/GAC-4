package com.test.gac_4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @GetMapping("/oauth2-callback")
    public String oauth2Callback(@RequestParam(required = false) String token) {
        if (token != null && !token.isEmpty()) {
            return "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Logging in...</title>" +
                    "<script>" +
                    "localStorage.setItem('jwtToken', '" + token + "');" +
                    "localStorage.setItem('token', '" + token + "');" +
                    "window.location.href = 'http://localhost:4200';" +
                    "</script>" +
                    "</head>" +
                    "<body>" +
                    "<p>Logging you in...</p>" +
                    "</body>" +
                    "</html>";
        }
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>Login Failed</title>" +
                "<script>window.location.href = 'http://localhost:4200/login?error=oauth';</script>" +
                "</head>" +
                "<body>" +
                "<p>Login failed. Redirecting...</p>" +
                "</body>" +
                "</html>";
    }
}