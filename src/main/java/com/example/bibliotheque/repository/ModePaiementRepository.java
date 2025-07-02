package com.example.bibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque.model.ModePaiement;

public interface ModePaiementRepository extends JpaRepository<ModePaiement, Integer> {
}
