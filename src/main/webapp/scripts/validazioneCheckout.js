
// validazione chekout (dati di spedizione)


// pattern regex

// Indirizzo: lettere, numeri, spazi, virgole e punti
const patternIndirizzo = /^[A-Za-zÀ-ù0-9 ,.]{5,}$/;

// Citta
const patternCitta = /^[A-Za-zÀ-ù ]{2,}$/;

// CAP solo 5 cifre 
const patternCap = /^[0-9]{5}$/;

// Provincia solo 2 lettere
const patternProvincia = /^[A-Za-z]{2}$/;


// valido campo praticamente la stessa per valutare i campi input della registrazione
function validaCampo(campo, pattern, span, messaggio) {
    if (campo.value.match(pattern)) {
        campo.classList.remove("error");
        span.innerHTML = "";
        return true;
    } else {
        campo.classList.add("error");
        span.innerHTML = messaggio;
        return false;
    }
}


// funzione per ogni campo

function validaIndirizzo() {
    let campo = document.getElementById("indirizzo");
    let span = document.getElementById("erroreIndirizzo");
    return validaCampo(campo, patternIndirizzo, span, "Inserisci un indirizzo valido (min 5 caratteri)");
}

function validaCitta() {
    let campo = document.getElementById("citta");
    let span = document.getElementById("erroreCitta");
    return validaCampo(campo, patternCitta, span, "Inserisci una citta' valida (solo lettere)");
}

function validaCap() {
    let campo = document.getElementById("cap");
    let span = document.getElementById("erroreCap");
    return validaCampo(campo, patternCap, span, "Il CAP deve essere di 5 cifre");
}

function validaProvincia() {
    let campo = document.getElementById("provincia");
    let span = document.getElementById("erroreProvincia");
    return validaCampo(campo, patternProvincia, span, "La provincia deve essere di 2 lettere (es. SA)");
}


// devono essere tutti true per partire
function validaFormCheckout() {
    let okIndirizzo = validaIndirizzo();
    let okCitta = validaCitta();
    let okCap = validaCap();
    let okProvincia = validaProvincia();

    return okIndirizzo && okCitta && okCap && okProvincia;
}