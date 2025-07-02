package com.example.bibliotheque.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotheque.model.Abonnement;
import com.example.bibliotheque.repository.AbonnementRepository;

@Service
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    public Abonnement enregistrerAbonnement(Abonnement abonnement) {

        // Vérifier s'il existe déjà un abonnement actif à la date d'abonnement
        List<Abonnement> actifs = abonnementRepository.findAbonnementActifPourDate(
                abonnement.getAdherent(),
                abonnement.getDateAbonnement()
        );

        if (!actifs.isEmpty()) {
            throw new RuntimeException("L'adhérent a déjà un abonnement actif pour cette période.");
        }

        // Calcul automatique de la date d’expiration (1 an après)
        abonnement.setDateExpiration(abonnement.getDateAbonnement().plusYears(1));

        return abonnementRepository.save(abonnement);
    }
}

