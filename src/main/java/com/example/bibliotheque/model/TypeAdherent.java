package com.example.bibliotheque.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_adherent")
public class TypeAdherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private int dateRetour;
    private int priorite;

    public TypeAdherent() {}

    public TypeAdherent(int id, String nom, int dateRetour, int priorite) {
        this.id = id;
        this.nom = nom;
        this.dateRetour = dateRetour;
        this.priorite = priorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(int dateRetour) {
        this.dateRetour = dateRetour;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public int getDureePret() {
        return dateRetour;
    }

    @Override
    public String toString() {
        return this.nom;
    }


}
