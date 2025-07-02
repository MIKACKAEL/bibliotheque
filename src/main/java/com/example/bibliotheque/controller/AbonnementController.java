package com.example.bibliotheque.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bibliotheque.model.Abonnement;
import com.example.bibliotheque.model.Adherent;
import com.example.bibliotheque.model.ModePaiement;
import com.example.bibliotheque.service.AbonnementService;
import com.example.bibliotheque.service.AdherentService;
import com.example.bibliotheque.service.ModePaiementService;

@Controller
@RequestMapping("/abonnements")
public class AbonnementController {

    @Autowired
    private AbonnementService abonnementService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private ModePaiementService modePaiementService;


@GetMapping("/ajouter")
public String afficherFormulaireAbonnement(@RequestParam(required = false) String success,Model model) {
    // Abonnement abonnement = new Abonnement();
    model.addAttribute("abonnement",new Abonnement());
    // abonnement.setModePaiement(new ModePaiement());
    model.addAttribute("adherents", adherentService.findAll());
    model.addAttribute("modePaiement", modePaiementService.findAll());

        if (success != null) {
        model.addAttribute("success", "Abonnement ajouté avec succès !");
    }

    return "abonnementForm";
}

    

    @PostMapping("/ajouter")
    public String enregistrerAbonnement(
            @RequestParam("adherentId") int adherentId,
            @RequestParam("modePaiementId") Integer modePaiementId,
            @RequestParam("dateAbonnement") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateAbonnement,
            @RequestParam("montant") BigDecimal montant,
            Model model) {

        try {
            // LocalDate dateAbonnement = LocalDate.now();
            Adherent adherent = adherentService.findById(adherentId);
            ModePaiement modePaiement = modePaiementService.findById(modePaiementId); 

            Abonnement abonnement = new Abonnement();
            abonnement.setAdherent(adherent);
            abonnement.setModePaiement(modePaiement); 
            abonnement.setDateAbonnement(dateAbonnement);
            abonnement.setMontant(montant);
            abonnement.setDateExpiration(dateAbonnement.plusYears(1)); // Bonus : auto expiration

            abonnementService.enregistrerAbonnement(abonnement);

            return "redirect:/abonnements/ajouter?success";

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("abonnement", new Abonnement());
            model.addAttribute("adherents", adherentService.findAll());
            model.addAttribute("modePaiement", modePaiementService.findAll());
            return "abonnementForm";
        } 
    }

}
