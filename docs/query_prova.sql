USE demarcaf;


DESCRIBE prodotto;
DESCRIBE ordine;
DESCRIBE dettaglio_ordine;


SELECT id_utente, email, ruolo, password FROM utente; 

SELECT id_prodotto, nome, prezzo, attivo FROM prodotto;

SELECT id_prodotto, nome, prezzo FROM prodotto WHERE attivo = true;

SELECT id_prodotto, nome FROM prodotto WHERE attivo = false;



SELECT
    d.id_ordine,
    p.nome AS prodotto,
    d.quantita,
    d.prezzo_unitario AS prezzo_pagato,
    p.prezzo AS prezzo_attuale
FROM dettaglio_ordine d
JOIN prodotto p ON d.id_prodotto = p.id_prodotto
ORDER BY d.id_ordine;

SELECT
    o.id_ordine,
    o.data,
    u.email AS cliente,
    o.totale,
    o.stato,
    o.citta,
    o.note
FROM ordine o
JOIN utente u ON o.id_utente = u.id_utente
ORDER BY o.data DESC;



SELECT
    d.id_ordine,
    p.nome AS nome_prodotto,
    d.quantita,
    d.prezzo_unitario,
    (d.quantita * d.prezzo_unitario) AS subtotale
FROM dettaglio_ordine d
JOIN prodotto p ON d.id_prodotto = p.id_prodotto
WHERE d.id_ordine = 7;



SELECT o.id_ordine, o.data, u.email, o.totale
FROM ordine o
JOIN utente u ON o.id_utente = u.id_utente
WHERE o.data >= '2026-07-01 00:00:00'
  AND o.data <= '2026-07-31 23:59:59'
ORDER BY o.data DESC;



SELECT o.id_ordine, o.data, u.email, o.totale
FROM ordine o
JOIN utente u ON o.id_utente = u.id_utente
WHERE u.email LIKE '%gmail%';



SELECT id_prodotto, nome, immagine FROM prodotto WHERE immagine IS NOT NULL;



SELECT c.nome AS categoria, p.nome AS prodotto, p.prezzo
FROM prodotto p
JOIN categoria c ON p.id_categoria = c.id_categoria
WHERE p.attivo = true
ORDER BY c.id_categoria, p.nome;