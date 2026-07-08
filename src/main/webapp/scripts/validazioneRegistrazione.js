// Verifico che sia valida la form di registrazione


const patternNome = /^[A-Za-zÀ-ù ]{2,}$/; //includo lettere maiuscole e minuscole e voglio almeno 2 caratteri apparte gli accenti

// Email
const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

// Password almeno 6 caratteri
const patternPassword = /^.{6,}$/;


//VALIDAZIONE
// Controlla un campo(input utente) con un pattern(regex) span fa apparire mess di errore .

function validaCampo(campo, pattern, span, messaggio) {
    if (campo.value.match(pattern)) //vedo se il campo combacia con la regex
		{
        // VALIDO risptta il pattern posso togliere la classe errore del css
        campo.classList.remove("error");
        span.innerHTML = "";
        return true;
    } else {
        // se non è valido aggiungo la classe error e mostro il messaggio nel DOM nello span
        campo.classList.add("error");
        span.innerHTML = messaggio;
        return false;
    }
}


// funzioni PER OGNI CAMPO ---

function validaNome() {
    let campo = document.getElementById("nome");
    let span = document.getElementById("erroreNome");
    return validaCampo(campo, patternNome, span, "Inserisci un nome valido (solo lettere, min 2)");
}

function validaCognome() {
    let campo = document.getElementById("cognome");
    let span = document.getElementById("erroreCognome");
    return validaCampo(campo, patternNome, span, "Inserisci un cognome valido (solo lettere, min 2)");
}

function validaEmail() {
    let campo = document.getElementById("email");
    let span = document.getElementById("erroreEmail");
    return validaCampo(campo, patternEmail, span, "Inserisci un'email valida");
}

function validaPassword() {
    let campo = document.getElementById("password");
    let span = document.getElementById("errorePassword");
    return validaCampo(campo, patternPassword, span, "La password deve avere almeno 6 caratteri");
}


// valido l'invio contrllando tutti i campi
function validaForm() {
    let okNome = validaNome(); //devono essere tutti true
    let okCognome = validaCognome();
    let okEmail = validaEmail();
    let okPassword = validaPassword();

   //devono essere tutti validi
    return okNome && okCognome && okEmail && okPassword;
}