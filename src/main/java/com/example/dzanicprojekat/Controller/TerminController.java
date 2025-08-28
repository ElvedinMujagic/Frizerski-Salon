package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Entities.Termin;
import com.example.dzanicprojekat.Services.FrizerService;
import com.example.dzanicprojekat.Services.TerminService;
import com.example.dzanicprojekat.Services.UslugeService;
import com.example.dzanicprojekat.Utility.DTOs.TerminDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    public String prihvaceniTermini(Authentication authentication, Model model) {
        Object principal = authentication.getPrincipal();
        List<Termin> termini = terminService.getAllUserTermins(principal.toString());
        termini.removeIf(t -> t.getDatum_termina().isBefore(LocalDate.now().minusDays(1)));
        model.addAttribute("termini",termini);
        model.addAttribute("title","Prihvaćeni Termini");
        return "/dashboard/client/prihvaceni_termini";
    }

    @GetMapping("/client/zakazi_termin")
    public String zakaziTermin(Model model) {
        model.addAttribute("terminDTO",new TerminDTO());
        prepareModel(model);
        return "/dashboard/client/zakazi_termin";
    }

    @GetMapping("/frizer/zakazani_termini")
    public String frizerZakazaniTermini(Authentication authentication, Model model) {
        Object principal = authentication.getPrincipal();
        model.addAttribute("termini",terminService.getAllFrizerTermins(principal.toString(),false));
        model.addAttribute("terminDTO",new TerminDTO());
        model.addAttribute("title","Zakazani termin");
        return "/dashboard/frizer/zakazani_termini";
    }

    @GetMapping("/frizer/odobreni_termini")
    public String frizerOdobreniTermini(Authentication authentication, Model model) {
        Object principal = authentication.getPrincipal();
        List<Termin> termini = terminService.getAllFrizerTermins(principal.toString(),true);
        termini.removeIf(t -> t.getDatum_termina().isBefore(LocalDate.now().minusDays(1)));
        model.addAttribute("termini",termini);
        model.addAttribute("terminDTO",new TerminDTO());
        model.addAttribute("title","Odobreni termini");
        return "/dashboard/frizer/odobreni_termini";
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
        terminService.addTermin(terminDTO);
        return "redirect:/client/zakazi_termin";
    }

    @PostMapping("/obrisi_termin_request")
    public String deleteTermin(@Valid @RequestParam Long id) {
        terminService.deleteTermin(id);
        return "redirect:/admin/svi_termini";
    }


    @PostMapping("/cancel_termin_request")
    public String cancelTermin(@ModelAttribute TerminDTO terminDTO) {
        terminService.cancelTermin(terminDTO);
        return "redirect:/frizer/zakazani_termini";
    }

    @PostMapping("/potvrdi_termin_request")
    public String potvrdiTermin(@ModelAttribute TerminDTO terminDTO, RedirectAttributes model) {
        LocalTime time = terminDTO.getTime();

        if(time==null) {
            model.addFlashAttribute("error","Unesite ispravno vrijeme 09:00 - 17:00");
            return "redirect:/frizer/zakazani_termini";
        }

        if(time.isBefore(LocalTime.parse("09:00")) || time.isAfter(LocalTime.parse("17:00"))) {
            model.addFlashAttribute("error","Unesite ispravno vrijeme 09:00 - 17:00");
            return "redirect:/frizer/zakazani_termini";
        }
        
        terminService.potvrdiTermin(terminDTO);
        return "redirect:/frizer/zakazani_termini";
    }

    private void prepareModel(Model model) {
        model.addAttribute("title","Zakaži Termin");
        model.addAttribute("usluge",uslugeService.getAllActiveUsluga());
        model.addAttribute("frizers",frizerService.getAvailableFrizers());
        model.addAttribute("minTime", LocalDate.now().plusDays(2));
        model.addAttribute("maxTime", LocalDate.now().plusMonths(2));
    }
}
