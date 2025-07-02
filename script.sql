-- Création de la base de données
CREATE DATABASE IF NOT EXISTS biblio;
USE biblio;

-- Table des types d’adhérents
CREATE TABLE type_adherent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    date_retour INT,
    priorite INT NOT NULL
);

-- Table des adhérents
CREATE TABLE adherent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_adh VARCHAR(10) UNIQUE,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    type_id INT NOT NULL,
    date_naissance DATE NOT NULL,
    piece_identite VARCHAR(100) NOT NULL,
    date_inscription DATE NOT NULL,
    FOREIGN KEY (type_id) REFERENCES type_adherent(id)
);

CREATE TABLE mode_paiement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE
);

-- Table des abonnements
CREATE TABLE abonnements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    adherent_id INT NOT NULL,
    mode_paiement_id INT NOT NULL,
    date_abonnement DATE NOT NULL,
    date_expiration DATE NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (adherent_id) REFERENCES adherent(id)
    FOREIGN KEY (mode_paiement_id) REFERENCES mode_paiement(id)
);




CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO type_adherent (nom, date_retour, priorite) VALUES 
('professeur', 30, 3),
('professionnel', 15, 2),
('etudiant', 7, 1);

INSERT INTO mode_paiement (nom) VALUES 
('Especes'),
('Carte bancaire'),
('Virement bancaire'),
('Mobile Money'),
('Cheque');

