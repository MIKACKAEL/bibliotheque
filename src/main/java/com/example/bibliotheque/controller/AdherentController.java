package com.example.bibliotheque.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bibliotheque.model.Abonnement;
import com.example.bibliotheque.model.Adherent;
import com.example.bibliotheque.model.ModePaiement;
import com.example.bibliotheque.service.AbonnementService;
import com.example.bibliotheque.service.AdherentService;
import com.example.bibliotheque.service.ModePaiementService;
import com.example.bibliotheque.service.TypeAdherentService;

@Controller
@RequestMapping("/adherents")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;


    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private TypeAdherentService typeAdherentService;

    @Autowired
    private ModePaiementService modePaiementService;

    

@GetMapping("/ajouter")
public String showForm(@RequestParam(required = false) String success,Model model) {
    model.addAttribute("adherent", new Adherent());
    model.addAttribute("types", typeAdherentService.findAll());
    model.addAttribute("modesPaiement", modePaiementService.findAll());
    model.addAttribute("today", LocalDate.now());
    

    if (success != null) {
        model.addAttribute("success", "Adhérent ajouté avec succès !");
    }
    return "ajouterAdherent";
}
    // private int calculerAge(LocalDate dateNaissance) {
    //     return Period.between(dateNaissance, LocalDate.now()).getYears();
    // }

    @PostMapping("/ajouter")
    public String ajouterAdherent(
            @ModelAttribute Adherent adherent,
            @RequestParam("modePaiementId") Integer modePaiementId,
            @RequestParam("montant") BigDecimal montant,
            Model model) {

        try {
            LocalDate today = LocalDate.now(); // date automatique

            // Vérification de la date de naissance
            if (adherent.getDateNaissance() == null) {
                model.addAttribute("error", "La date de naissance est obligatoire.");
                model.addAttribute("adherent", adherent);
                model.addAttribute("modesPaiement", modePaiementService.findAll());
                model.addAttribute("types", typeAdherentService.findAll());
                model.addAttribute("today", today);
                return "ajouterAdherent";
            }

            // Calculer l'âge à partir de la date de naissance
            int age = Period.between(adherent.getDateNaissance(), today).getYears();
            if (age < 12) {
                model.addAttribute("error", "L'âge minimum pour l'inscription est de 12 ans.");
                model.addAttribute("adherent", adherent);
                model.addAttribute("modesPaiement", modePaiementService.findAll());
                model.addAttribute("types", typeAdherentService.findAll());
                model.addAttribute("today", today);
                return "ajouterAdherent";
            }

            adherent.setDateInscription(today);
            Adherent savedAdherent = adherentService.ajouterAdherent(adherent);
            ModePaiement modePaiement = modePaiementService.findById(modePaiementId);

            Abonnement abonnement = new Abonnement();
            abonnement.setAdherent(savedAdherent);
            abonnement.setDateAbonnement(today);
            abonnement.setMontant(montant);
            abonnement.setDateExpiration(today.plusYears(1));
            abonnement.setModePaiement(modePaiement);

            abonnementService.enregistrerAbonnement(abonnement);

            return "redirect:/adherents/ajouter?success";

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("adherent", adherent);
            model.addAttribute("types", typeAdherentService.findAll());
             model.addAttribute("modesPaiement", modePaiementService.findAll());
            model.addAttribute("today", LocalDate.now());
            return "ajouterAdherent";
        }
    }


}


