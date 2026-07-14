
const patternNome = /^[A-Za-zÀ-ù ]{2,}$/; 

const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

const patternPassword = /^.{6,}$/;




function validaCampo(campo, pattern, span, messaggio) {
    if (campo.value.match(pattern)) 
		{
        campo.classList.remove("error");
        span.innerHTML = "";
        return true;
    } else {
      
        campo.classList.add("error");
        span.innerHTML = messaggio;
        return false;
    }
}



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


function validaForm() {
    let okNome = validaNome(); 
    let okCognome = validaCognome();
    let okEmail = validaEmail();
    let okPassword = validaPassword();

    return okNome && okCognome && okEmail && okPassword;
}