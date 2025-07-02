package com.example.bibliotheque.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "adherent")
public class Adherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroAdh;
    private String nom;
    private String email;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeAdherent typeAdherent;
    private LocalDate dateNaissance;
    private String pieceIdentite;
    private LocalDate dateInscription;


    public Adherent() {}

    public Adherent(int id, String numeroAdh ,String nom, String email, LocalDate dateNaissance, String pieceIdentite, LocalDate dateInscription, TypeAdherent typeAdherent) {
        this.id = id;
        this.numeroAdh = numeroAdh;
        this.nom = nom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.pieceIdentite = pieceIdentite;
        this.dateInscription = dateInscription;
        this.typeAdherent = typeAdherent;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroAdh() {
        return numeroAdh;
    }

    public void setNumeroAdh(String numeroAdh) {
        this.numeroAdh = numeroAdh;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPieceIdentite() {
        return pieceIdentite;
    }

    public void setPieceIdentite(String pieceIdentite) {
        this.pieceIdentite = pieceIdentite;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
}

