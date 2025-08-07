document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("Login-Form")
    const registerForm = document.getElementById("Register-Form");
    const loginBtn = document.getElementById("LoginBtn");
    const registerBtn = document.getElementById("RegisterBtn");
    const LoginPopupOverlay = document.getElementById("LoginPopupOverlay");
    const RegisterPopupOverlay = document.getElementById("RegisterPopupOverlay")

    const errorOverlays = document.getElementsByClassName("error-style")

    loginBtn.addEventListener("click", function () {
        LoginPopupOverlay.style.display = "block";
        Array.from(errorOverlays).forEach(overlay => {
            overlay.style.display = "none"
        })
        loginForm.reset();
    });

    registerBtn.addEventListener("click",function () {
        RegisterPopupOverlay.style.display="block";
        Array.from(errorOverlays).forEach(overlay => {
            overlay.style.display = "none"
        })
        registerForm.reset();
    })
    // Zatvara overlay klikom izvana ili dugmetom ESC
    window.addEventListener("click", function (e) {
        // Zatvara overlay klikom na dugme login
        if (e.target === LoginPopupOverlay) {
            LoginPopupOverlay.style.display = "none";
        }
    });
    window.addEventListener("click",function (e) {
        if (e.target === RegisterPopupOverlay) {
            RegisterPopupOverlay.style.display="none";
        }
    });
    window.addEventListener("keydown", function (e) {
        if (e.key === "Escape") {
            LoginPopupOverlay.style.display = "none";
        }
    });
    window.addEventListener("keydown",function (e) {
        if (e.key==="Escape") {
            RegisterPopupOverlay.style.display="none";
        }
    })

    loginForm.addEventListener("submit", async function(event) {
        event.preventDefault();
        const formData = new FormData(loginForm);
        const response = await fetch("/login", {
            method:"POST",
            body:formData,
        })
        if(response.ok) {
            alert("Uspjesan login")
            const data = await response.json();
            if (data.redirect) {
                window.location.href = data.redirect
            }
        } else {
            const errors = await response.json();
            console.log(errors)
            Array.from(errorOverlays).forEach(overlay => {
                overlay.style.display = "block"
            });
            document.getElementById("error-login").textContent = errors.error
        }
    })

    registerForm.addEventListener("submit",  async function(event) {
        event.preventDefault();
        const formData = new FormData(registerForm);
        const response = await fetch("/register", {
            method:"POST",
            body:formData});
        if (response.ok) {
            const data = await response.json();
            if (data.redirect) {
                window.location.href=data.redirect;
            }
        } else {
            const errors = await response.json();
            console.log(errors)
            Array.from(errorOverlays).forEach(overlay => {
                overlay.style.display = "block"
            })
            document.getElementById("error-email").textContent = errors.email
            document.getElementById("error-password").textContent = errors.password
            document.getElementById("error-ime-register").textContent = errors.ime
            document.getElementById("error-prezime").textContent = errors.prezime
            document.getElementById("error-username").textContent = errors.username
            document.getElementById("error-confPass").textContent = errors.confPass
        }
    });
});

