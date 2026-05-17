package com.test.gac_4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void sendOrganisationValidationEmail(String email, String orgName) {
        if (mailSender == null) return;
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Organization Has Been Validated");
            message.setText("Dear " + orgName + ",\n\nCongratulations! Your organization has been validated and is now active on our platform.\n\nBest regards,\nGAC Team");
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    public void sendOrganisationRejectionEmail(String email, String orgName) {
        if (mailSender == null) return;
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Organization Registration Rejected");
            message.setText("Dear " + orgName + ",\n\nUnfortunately, your organization registration has been rejected. Please contact support for more information.\n\nBest regards,\nGAC Team");
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    public void sendDonationConfirmationEmail(String userEmail, String userName, Double amount, String actionTitle) {
        if (mailSender == null) return;
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userEmail);
            message.setSubject("Donation Confirmation");
            message.setText("Dear " + userName + ",\n\nThank you for your generous donation of $" + amount + " to the action: " + actionTitle + ".\n\nYour contribution makes a real difference!\n\nBest regards,\nGAC Team");
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    public void sendActionUpdateEmail(String email, String actionTitle, String update) {
        if (mailSender == null) return;
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Action Update: " + actionTitle);
            message.setText("Dear Supporter,\n\nThere's an important update about " + actionTitle + ":\n\n" + update + "\n\nBest regards,\nGAC Team");
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
