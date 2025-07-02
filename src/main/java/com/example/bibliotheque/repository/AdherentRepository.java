package com.example.bibliotheque.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque.model.Adherent;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPieceIdentite(String pieceIdentite);
}
