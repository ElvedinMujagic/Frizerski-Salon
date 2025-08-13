package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Services.UslugeService;
import com.example.dzanicprojekat.Utility.DTOs.UslugaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UslugeController {
    private final UslugeService uslugeService;

    public UslugeController(UslugeService uslugeService) {
        this.uslugeService = uslugeService;
    }

    @GetMapping("/admin/sve_usluge")
    public String sveUsluge(Model model) {
        model.addAttribute("title","Sve Usluge");
        model.addAttribute("usluge",uslugeService.getAllUsluga());
        return "/dashboard/admin/sve_usluge";
    }

    @GetMapping("/admin/dodaj_usluge")
    public String dodajUsluge(Model model) {
        model.addAttribute("title","Dodaj Usluge");
        model.addAttribute("uslugaDTO", new UslugaDTO());
        return "/dashboard/admin/dodaj_usluge";
    }

    @PostMapping("/dodaj_uslugu")
    public String dodajUslugu(@Valid @ModelAttribute UslugaDTO uslugaDTO, BindingResult bindingResult,Model model) {
        System.out.println("Dodaj uslugu - End point hit");
        // TODO: Onemoguciti dodavanje ako usluga vec postoji (Ako imaju isti naziv)
        if (bindingResult.hasErrors()) {
            model.addAttribute("title","Dodaj Usluge");
            return "/dashboard/admin/dodaj_usluge";
        }
        uslugeService.addUsluga(uslugaDTO);
        return "redirect:/admin/dodaj_usluge";
    }

    @PostMapping("/obrisi_uslugu")
    public String obrisiUslugu(@Valid @RequestParam Long id) {
        System.out.println("Delete endpoint hit");
        if (uslugeService.checkByID(id)) {
            uslugeService.deleteUsluga(id);
        }
        return "redirect:/admin/sve_usluge";
    }
}
