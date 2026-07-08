//carica i comuni di una provincia senza ricaricare la pagina

// quando l'utente cambia la provincia
function caricaComuni() {

    //  provincia scelta dall'utente 
    let provincia = document.getElementById("provincia").value;

    //  menu dei comuni 
    let menuComuni = document.getElementById("citta");

    // nel caso non ci sia stata la scelta 
    if (provincia === "") {
        menuComuni.innerHTML = "<option value=''>-- Prima scegli la provincia --</option>";
        return;
    }

    //  XMLHttpRequest
    let richiesta = new XMLHttpRequest();

    
    richiesta.onreadystatechange = function() {
       
        if (richiesta.readyState === 4 && richiesta.status === 200) {

            // da json ad array JavaScript
            let comuni = JSON.parse(richiesta.responseText);

           
            menuComuni.innerHTML = "<option value=''>-- Seleziona un comune --</option>";

            // Per ogni comune ricevuto creo dinamicamenet
            for (let i = 0; i < comuni.length; i++) {
                let opzione = document.createElement("option");
                opzione.value = comuni[i];
                opzione.text = comuni[i];
                menuComuni.appendChild(opzione);
            }
        }
    };

    // verso la ComuniServlet
    richiesta.open("GET", "ComuniServlet?provincia=" + provincia, true);

    
    richiesta.send();
}