package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Services.AdminService;
import com.example.dzanicprojekat.Services.FrizerService;
import com.example.dzanicprojekat.Services.UserService;
import com.example.dzanicprojekat.Utility.DTOs.FrizerDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FrizerController {
    private final UserService userService;
    private final AdminService adminService;
    private final FrizerService frizerService;

    public FrizerController(UserService userService, AdminService adminService, FrizerService frizerService) {
        this.userService = userService;
        this.adminService = adminService;
        this.frizerService = frizerService;
    }

    @GetMapping("/admin/svi_frizeri_page")
    public String getFrizers(Model model) {
        model.addAttribute("user_data",userService.getFrizersOnly());
        return "/dashboard/admin/svi_frizeri";
    }

    @GetMapping("/admin/dodaj_frizera_page")
    public String getForm(Model model) {
        model.addAttribute("title","Dodaj Frizera");
        model.addAttribute("users", userService.getClientsOnly());
        model.addAttribute("frizerDTO", new FrizerDTO());
        return "/dashboard/admin/dodaj_frizera";
    }

    @PostMapping("/dodaj_frizera_request")
    public String dodajFrizera(@Valid @ModelAttribute FrizerDTO frizerDTO, BindingResult result,Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title","Dodaj Frizera");
            return "/dashboard/admin/dodaj_frizera";
        }
        /* TODO: Napraviti validaciju za:
        *   1.) Ako korisnik unese ID koji je zauzet/nepostoji
        *   2.) Napraviti da koristi se Username ili ID
        *   3.) Ako korisnik unese negativan broj */
        System.out.println("TEST");
        frizerService.addFrizer(frizerDTO);
        return "redirect:/admin/dodaj_frizera_page";
    }

    @PostMapping("/obrisi_frizera_request")
    public String obrisiFrizera(@Valid @RequestParam Long id) {
        frizerService.deleteFrizer(id);
        return "redirect:/admin/svi_frizeri_page";
    }
}
