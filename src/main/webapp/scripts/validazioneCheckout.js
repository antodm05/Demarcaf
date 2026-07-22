const patternIndirizzo = /^[A-Za-zÀ-ù0-9 ,.'-]{5,}$/;

const patternCitta = /^[A-Za-zÀ-ù '-]{2,}$/;

const patternCap = /^[0-9]{5}$/;

const patternProvincia = /^[A-Za-z]{2}$/;


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


function validaFormCheckout() {
    let okIndirizzo = validaIndirizzo();
    let okCitta = validaCitta();
    let okCap = validaCap();
    let okProvincia = validaProvincia();

    return okIndirizzo && okCitta && okCap && okProvincia;
}