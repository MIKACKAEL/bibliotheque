package com.example.bibliotheque.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bibliotheque.model.Abonnement;
import com.example.bibliotheque.model.Adherent;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

    @Query("SELECT a FROM Abonnement a WHERE a.adherent = :adherent " +
           "AND :date BETWEEN a.dateAbonnement AND a.dateExpiration")
    List<Abonnement> findAbonnementActifPourDate(@Param("adherent") Adherent adherent,
                                                  @Param("date") LocalDate date);

    @Query("SELECT a FROM Abonnement a WHERE a.adherent = :adherent " +
           "AND FUNCTION('MONTH', a.dateAbonnement) = FUNCTION('MONTH', :date) " +
           "AND FUNCTION('YEAR', a.dateAbonnement) = FUNCTION('YEAR', :date)")
    List<Abonnement> findAbonnementPourMoisEtAnnee(@Param("adherent") Adherent adherent,
                                                    @Param("date") LocalDate date);
}
