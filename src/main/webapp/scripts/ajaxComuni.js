
function caricaComuni() {

    let provincia = document.getElementById("provincia").value;

    let menuComuni = document.getElementById("citta");

    if (provincia === "") {
        menuComuni.innerHTML = "<option value=''>-- Prima scegli la provincia --</option>";
        return;
    }

    let richiesta = new XMLHttpRequest();

    
    richiesta.onreadystatechange = function() {
       
        if (richiesta.readyState === 4 && richiesta.status === 200) {

            let comuni = JSON.parse(richiesta.responseText);

           
            menuComuni.innerHTML = "<option value=''>-- Seleziona un comune --</option>";

            for (let i = 0; i < comuni.length; i++) {
                let opzione = document.createElement("option");
                opzione.value = comuni[i];
                opzione.text = comuni[i];
                menuComuni.appendChild(opzione);
            }
        }
    };

    richiesta.open("GET", "ComuniServlet?provincia=" + provincia, true);

    
    richiesta.send();
}