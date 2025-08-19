package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Services.FrizerService;
import com.example.dzanicprojekat.Services.TerminService;
import com.example.dzanicprojekat.Services.UslugeService;
import com.example.dzanicprojekat.Utility.DTOs.TerminDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class TerminController {

    private final UslugeService uslugeService;
    private final FrizerService frizerService;
    private final TerminService terminService;

    public TerminController(UslugeService uslugeService, FrizerService frizerService, TerminService terminService) {
        this.uslugeService = uslugeService;
        this.frizerService = frizerService;
        this.terminService = terminService;
    }

    @GetMapping("/client/prihvaceni_termini")
    public String prihvaceniTermini(Model model) {
        model.addAttribute("title","Prihvaćeni Termini");
        return "/dashboard/client/prihvaceni_termini";
    }

    @GetMapping("/client/zakazi_termin")
    public String zakaziTermin(Model model) {
        model.addAttribute("terminDTO",new TerminDTO());
        prepareModel(model);
        return "/dashboard/client/zakazi_termin";
    }

    @GetMapping("/admin/svi_termini")
    public String sviTerminiAdmin(Model model) {
        model.addAttribute("title","Svi Termin");
        model.addAttribute("allTermin",terminService.getAllTermins());
        return "/dashboard/admin/svi_termini";
    }

    @PostMapping("/dodaj_termin_request")
    public String addTermin(@Valid @ModelAttribute TerminDTO terminDTO, BindingResult result , Model model) {
        if (result.hasErrors()) {
            prepareModel(model);
            return "/dashboard/client/zakazi_termin";
        }
        terminService.dodajTermin(terminDTO);
        return "redirect:/client/zakazi_termin";
    }

    @PostMapping("/obrisi_termin_request")
    public String deleteTermin(@Valid @RequestParam Long id) {
        return "redirect:/admin/svi_termini";
    }



    private void prepareModel(Model model) {
        model.addAttribute("title","Zakaži Termin");
        model.addAttribute("usluge",uslugeService.getAllActiveUsluga());
        model.addAttribute("frizers",frizerService.getAvailableFrizers());
        model.addAttribute("minTime", LocalDate.now().plusDays(2));
        model.addAttribute("maxTime", LocalDate.now().plusMonths(2));
    }
}
