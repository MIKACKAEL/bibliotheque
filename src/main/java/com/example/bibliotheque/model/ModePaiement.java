package com.example.bibliotheque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mode_paiement")
public class ModePaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    public ModePaiement() {}

    public ModePaiement (int id,String nom) {
        this.id = id;
        this.nom = nom;

    }

    public int  getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
