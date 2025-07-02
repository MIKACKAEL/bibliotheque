package com.example.bibliotheque.model;


import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "abonnements")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adherent_id", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "mode_paiement_id")
    private ModePaiement modePaiement;

    private LocalDate dateAbonnement;
    private LocalDate dateExpiration;
    private BigDecimal montant;

    public Abonnement() {}

    public Abonnement(Long id, Adherent adherent, ModePaiement modePaiement ,LocalDate dateAbonnement, LocalDate dateExpiration, BigDecimal montant) {
        this.id = id;
        this.adherent = adherent;
        this.modePaiement = modePaiement;
        this.dateAbonnement = dateAbonnement;
        this.dateExpiration = dateExpiration;
        this.montant = montant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public LocalDate getDateAbonnement() {
        return dateAbonnement;
    }

    public void setDateAbonnement(LocalDate dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
}
