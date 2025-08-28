document.addEventListener("DOMContentLoaded", () => {
    const openOtkaziBtn = document.getElementById("openOtkaziOverlayBtn");
    const closeOtkaziBtn = document.getElementById("closeOtkaziOverlayBtn");
    const otkazi_overlay = document.getElementById("otkazi_overlay");
    const otkaziTermin = document.getElementById("otkaziForm");

    const openPotvrdiBtn = document.getElementById("greenBtn");
    const closePotvrdiBtn = document.getElementById("closePotvrdiOverlay");
    const potvrdi_overlay= document.getElementById("potvrdi_overlay");
    const potvrdiTermin = document.getElementById("potvrdiForm");


    openOtkaziBtn.addEventListener("click", () => {
        otkazi_overlay.style.display = "block";
    });

    closeOtkaziBtn.addEventListener("click", () => {
        otkazi_overlay.style.display = "none";
        otkaziTermin.reset();
    });

    openPotvrdiBtn.addEventListener("click", () => {
        potvrdi_overlay.style.display = "block";
    });

    closePotvrdiBtn.addEventListener("click", () => {
        potvrdi_overlay.style.display = "none";
        potvrdiTermin.reset();
    });
});