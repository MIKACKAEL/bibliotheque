package com.example.bibliotheque.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotheque.model.Adherent;
import com.example.bibliotheque.repository.AdherentRepository;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public Adherent ajouterAdherent(Adherent adherent) {
        if (adherentRepository.existsByEmail(adherent.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        if (adherentRepository.existsByPieceIdentite(adherent.getPieceIdentite())) {
            throw new RuntimeException("Pièce d'identité déjà utilisée");
        }

        // Génération du numéro adhérent : ADH + ID après sauvegarde
        adherent.setDateInscription(LocalDate.now());
        adherent = adherentRepository.save(adherent); // Sauvegarde pour récupérer l'ID
        String numero = String.format("ADH%04d", adherent.getId());
        adherent.setNumeroAdh(numero);

        return adherentRepository.save(adherent); // Mise à jour avec le numéro
    }
        public List<Adherent> findAll() {
        return adherentRepository.findAll();
    }

    public Adherent findById(Integer id) {
        return adherentRepository.findById(id).orElse(null);
    }
}
