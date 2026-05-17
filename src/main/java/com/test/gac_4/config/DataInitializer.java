package com.test.gac_4.config;

import com.test.gac_4.entities.*;
import com.test.gac_4.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private OrganisationRepo organisationRepo;

    @Autowired
    private ActionChariteRepo actionRepo;

    @Autowired
    private usersrepo usersRepo;

    @Autowired
    private DonRepo donRepo;

    @Override
    public void run(String... args) {
        if (organisationRepo.count() > 0) return;

        // Create sample organizations
        Organisation org1 = createOrg("Hope Foundation", "123 Charity St, New York", "contact@hope.org", "+1-555-0101", "Providing education and healthcare to underprivileged communities worldwide.", "https://hopefoundation.org");
        Organisation org2 = createOrg("Green Earth Initiative", "456 Eco Ave, Portland", "info@greenearth.org", "+1-555-0102", "Environmental conservation and sustainability projects across the globe.", "https://greenearth.org");
        Organisation org3 = createOrg("Children First", "789 Kids Blvd, Chicago", "hello@childrenfirst.org", "+1-555-0103", "Dedicated to improving the lives of children through education and nutrition.", "https://childrenfirst.org");
        Organisation org4 = createOrg("Health for All", "321 Wellness Dr, Boston", "info@healthforall.org", "+1-555-0104", "Making healthcare accessible to everyone regardless of their economic status.", "https://healthforall.org");
        Organisation org5 = createOrg("Disaster Relief Team", "555 Rescue Rd, Houston", "help@disasterrelief.org", "+1-555-0105", "Rapid response disaster relief and humanitarian aid organization.", "https://disasterrelief.org");

        // Create sample charity actions
        createAction("Education for Underprivileged Children", "Help provide quality education to 500 children in rural areas. Your donation covers school supplies, teachers salaries, and infrastructure.", CategoryEnum.EDUCATION, LocalDate.now().plusMonths(6), "Rural Morocco", 50000.0, org1);
        createAction("Clean Water for Villages", "Fund the installation of water purification systems in 20 villages, providing clean drinking water to over 10,000 people.", CategoryEnum.ENVIRONMENT, LocalDate.now().plusMonths(3), "Atlas Mountains", 30000.0, org2);
        createAction("Emergency Food Distribution", "Distribute emergency food packages to families affected by the recent floods. Each package feeds a family of 5 for one month.", CategoryEnum.DISASTER_RELIEF, LocalDate.now().plusMonths(1), "Flood Affected Regions", 25000.0, org5);
        createAction("School Building Project", "Construct a new school building with 12 classrooms, a library, and sports facilities for 600 students.", CategoryEnum.EDUCATION, LocalDate.now().plusMonths(9), "Marrakech", 100000.0, org1);
        createAction("Free Medical Clinic", "Establish a mobile medical clinic serving remote communities with free checkups, medicines, and basic treatments.", CategoryEnum.HEALTH, LocalDate.now().plusMonths(4), "Remote Villages", 45000.0, org4);
        createAction("Tree Planting Campaign", "Plant 50,000 trees across deforested areas to combat climate change and restore natural habitats.", CategoryEnum.ENVIRONMENT, LocalDate.now().plusMonths(2), "Forest Regions", 20000.0, org2);
        createAction("Nutrition Program for Children", "Provide daily nutritious meals to 1000 children in underserved schools, improving their health and ability to learn.", CategoryEnum.POVERTY, LocalDate.now().plusMonths(5), "Casablanca", 35000.0, org3);
        createAction("Animal Shelter Support", "Support local animal shelters with food, medical care, and adoption programs for abandoned pets.", CategoryEnum.ANIMAL_WELFARE, LocalDate.now().plusMonths(3), "Rabat", 15000.0, org2);
        createAction("Winter Clothing Drive", "Distribute warm clothing, blankets, and heating supplies to families in need during the harsh winter months.", CategoryEnum.POVERTY, LocalDate.now().plusMonths(1), "Mountain Regions", 18000.0, org3);
        createAction("COVID-19 Relief Fund", "Provide medical equipment, PPE, and vaccines to underfunded hospitals and clinics.", CategoryEnum.HEALTH, LocalDate.now().plusMonths(2), "Nationwide", 75000.0, org4);
        createAction("Flood Reconstruction", "Rebuild homes and infrastructure destroyed by the recent floods, helping families return to normal life.", CategoryEnum.DISASTER_RELIEF, LocalDate.now().plusMonths(8), "Flood Areas", 120000.0, org5);
        createAction("Youth Skills Training", "Provide vocational training and job placement assistance to 200 unemployed youth.", CategoryEnum.EDUCATION, LocalDate.now().plusMonths(4), "Tangier", 28000.0, org1);
        createAction("Elderly Care Program", "Support elderly citizens with healthcare, companionship, and daily necessities through community outreach.", CategoryEnum.HEALTH, LocalDate.now().plusMonths(6), "Fes", 22000.0, org4);
        createAction("Ocean Cleanup Project", "Organize coastal cleanup drives and install waste management systems to protect marine life.", CategoryEnum.ENVIRONMENT, LocalDate.now().plusMonths(3), "Coastal Cities", 16000.0, org2);
        createAction("Orphanage Support", "Provide food, education, and emotional support to orphaned children in local orphanages.", CategoryEnum.POVERTY, LocalDate.now().plusMonths(5), "Multiple Cities", 40000.0, org3);
    }

    private Organisation createOrg(String nom, String adresse, String email, String phone, String description, String website) {
        Organisation org = new Organisation();
        org.setNom(nom);
        org.setAdresse(adresse);
        org.setEmail(email);
        org.setPhone(phone);
        org.setDescription(description);
        org.setWebsite(website);
        org.setValide(true);
        org.setIsActive(true);
        org.setCreatedAt(LocalDateTime.now());
        org.setUpdatedAt(LocalDateTime.now());
        return organisationRepo.save(org);
    }

    private void createAction(String titre, String description, CategoryEnum category, LocalDate dateAction, String lieu, Double objectifMontant, Organisation org) {
        ActionCharite action = new ActionCharite();
        action.setTitre(titre);
        action.setDescription(description);
        action.setCategory(category);
        action.setDateAction(dateAction);
        action.setLieu(lieu);
        action.setObjectifMontant(objectifMontant);
        action.setMontantCollecte(Math.random() * objectifMontant * 0.4);
        action.setOrganisation(org);
        action.setCreatedAt(LocalDateTime.now().minusDays((long)(Math.random() * 60)));
        action.setUpdatedAt(LocalDateTime.now());
        actionRepo.save(action);
    }
}
