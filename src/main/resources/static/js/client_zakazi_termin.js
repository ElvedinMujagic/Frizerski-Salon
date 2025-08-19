document.addEventListener("DOMContentLoaded", () => {
    const dateInput = document.getElementById("date_picker");
    const frizerInput = document.getElementById("frizer_picker");
    const uslugeInput = document.getElementById("usluga_picker");
    const addUslugaButton =document.getElementById("add_usluga")
    const uslugeList =document.getElementById("usluge")
    const cijenaEl = document.getElementById("cijena");

    const addTerminForm = document.getElementById("add_termin");
    /*
    var odabraneUsluge =[]
    addTerminForm.addEventListener("submit",async function(event) {
        event.preventDefault()
        const formData = new FormData(addTerminForm)
        formData.append("ukupnaCijena",ukupnaCijena)
        formData.append("odabraneUsluge",odabraneUsluge)
        const response = await fetch("/dodaj_termin_request",{method:"POST",body:formData});
        if (response.ok) {
            alert("Termin uspjesno poslan i primljen")
            const ok_response = await response.json();
            console.log(ok_response)
        } else {
            const error_response = await response.json();
            console.log(error_response)
        }
    })

    var ukupnaCijena= 0;
    addUslugaButton.addEventListener("click",()=> {
        const newListItem = document.createElement("li");
        newListItem.textContent = uslugeInput.options[uslugeInput.selectedIndex];
        odabraneUsluge.push(newListItem);
        var cur_position = newListItem.textContent.indexOf("-") + 1
        let cijena_string = newListItem.textContent.substring(cur_position,cur_position + 4);
        ukupnaCijena = ukupnaCijena + Number(cijena_string)
        cijenaEl.textContent = "Ukupna Cijena: " + ukupnaCijena + " KM";
        uslugeList.appendChild(newListItem);
    }); */


});


