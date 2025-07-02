package com.example.bibliotheque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotheque.model.ModePaiement;
import com.example.bibliotheque.repository.ModePaiementRepository;

@Service
public class ModePaiementService {

    @Autowired
    private ModePaiementRepository modePaiementRepository;

    public List<ModePaiement> findAll() {
        return modePaiementRepository.findAll();
    }
    public ModePaiement findById(Integer id) {
        return modePaiementRepository.findById(id).orElse(null);
    }
}

