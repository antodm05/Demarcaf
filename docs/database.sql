CREATE DATABASE IF NOT EXISTS demarcaf;
USE demarcaf;

CREATE TABLE utente (
    id_utente INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password CHAR(128) NOT NULL,
    ruolo VARCHAR(10) NOT NULL DEFAULT 'cliente',
    nome VARCHAR(100) NOT NULL,
    cognome VARCHAR(100) NOT NULL
);

CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE prodotto (
    id_prodotto INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descrizione TEXT,
    prezzo DECIMAL(10,2) NOT NULL,
    quantita INT NOT NULL DEFAULT 0,
    immagine VARCHAR(255),
    attivo BOOLEAN NOT NULL DEFAULT TRUE,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);


CREATE TABLE ordine (
    id_ordine INT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    totale DECIMAL(10,2) NOT NULL,
    indirizzo VARCHAR(255) NOT NULL,
    citta VARCHAR(100) NOT NULL,
    cap VARCHAR(5) NOT NULL,
    provincia VARCHAR(2),
    metodo_pagamento VARCHAR(20) NOT NULL,
    stato VARCHAR(20) NOT NULL DEFAULT 'in elaborazione',
    id_utente INT NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES utente(id_utente)
);

CREATE TABLE dettaglio_ordine (
    id_ordine INT NOT NULL,
    id_prodotto INT NOT NULL,
    quantita INT NOT NULL,
    prezzo_unitario DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_ordine, id_prodotto),
    FOREIGN KEY (id_ordine) REFERENCES ordine(id_ordine),
    FOREIGN KEY (id_prodotto) REFERENCES prodotto(id_prodotto)
);


INSERT INTO categoria (nome) VALUES
('Caffè'),
('Accessori'),
('Marketing'),
('Servizi');

INSERT INTO utente (email, password, ruolo, nome, cognome) VALUES
('admin@demarcaf.it', SHA2('admin123', 512), 'admin', 'Antonio', 'Admin');


INSERT INTO prodotto (nome, descrizione, prezzo, quantita, id_categoria) VALUES
('Caffè Miscela Classica 1kg', 'Miscela di caffè in grani, gusto pieno e corposo', 12.50, 100, 1),
('Cialde Espresso x50', 'Confezione da 50 cialde compostabili', 15.00, 200, 1),
('Tazzina da caffè DEMARCAF', 'Tazzina in porcellana con logo aziendale', 4.90, 50, 2),
('Assistenza tecnica H24', 'Intervento tecnico su macchine da caffè', 60.00, 999, 4);


SELECT * FROM categoria;
SELECT * FROM utente;
SELECT * FROM prodotto;