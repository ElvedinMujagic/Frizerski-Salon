document.addEventListener("DOMContentLoaded", () => {
    const avatarBtn = document.getElementById("avatarBtn");
    const dropdownMenu = document.getElementById("dropdownMenu");

    if (avatarBtn && dropdownMenu) {
        avatarBtn.addEventListener("click", () => {
            dropdownMenu.style.display =
                dropdownMenu.style.display === "block" ? "none" : "block";
        });
    }

    window.addEventListener("click", (e) => {
        if (!avatarBtn.contains(e.target) && !dropdownMenu.contains(e.target)) {
            dropdownMenu.style.display = "none";
        }
    });
});

