package com.example.dzanicprojekat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/info/home")
    public String home() {
        return "index.html";
    }

    @GetMapping("/info/onama")
    public String Onama() {
        return "/info/O nama";
    }

    @GetMapping("/info/kontakt")
    public String Kontakt() {
        return "/info/Kontakt";
    }

    @GetMapping("/info/cijene")
    public String Cijene() {
        return "/info/Cijene";
    }

    @GetMapping("/info/termini")
    public String Termini() {
        return "/info/Termini";
    }

    @GetMapping("/general/edit_profile")
    public String editProfile(Model model) {
        model.addAttribute("title","Editing Profile");
        return "/dashboard/general/edit_profile";
    }

    @GetMapping("/general/settings")
    public String settingsProfile(Model model) {
        model.addAttribute("title","Settings");
        return "/dashboard/general/settings";
    }

    @GetMapping("/")
    public String clientLogin() {
        return "index.html";
    }
}
