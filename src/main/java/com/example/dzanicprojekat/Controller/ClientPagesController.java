package com.example.dzanicprojekat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientPagesController {

    @GetMapping("/client/prihvaceni_termini")
    public String prihvaceniTermini(Model model) {
        model.addAttribute("title","Prihvaćeni Termini");
        return "/dashboard/client/prihvaceni_termini";
    }

    @GetMapping("/client/zakazi_termin")
    public String zakaziTermin(Model model) {
        model.addAttribute("title","Zakaži Termin");
        return "/dashboard/client/zakazi_termin";
    }
}
