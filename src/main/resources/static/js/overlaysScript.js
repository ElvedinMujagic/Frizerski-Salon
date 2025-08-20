document.addEventListener("DOMContentLoaded", () => {
    const openBtn = document.getElementById("openOverlayBtn");
    const closeBtn = document.getElementById("closeOverlayBtn");
    const overlay = document.getElementById("overlay");
    const otkaziTermin = document.getElementById("cancelForm");

// Show overlay
    openBtn.addEventListener("click", () => {
        overlay.style.display = "block";
    });

// Hide overlay
    closeBtn.addEventListener("click", () => {
        overlay.style.display = "none";
        otkaziTermin.reset();
    });
});