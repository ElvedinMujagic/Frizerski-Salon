package com.example.dzanicprojekat.Controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("title","Svi Frizeri");
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
    public String dodajFrizera(@Valid @ModelAttribute FrizerDTO frizerDTO, RedirectAttributes model) {
        if(frizerDTO.getId()==null) {
            model.addFlashAttribute("error","Unesite ID korisnika!");
            return "redirect:/admin/dodaj_frizera_page";
        }

        if(!frizerService.checkIfClient(frizerDTO.getId())) {
            model.addFlashAttribute("error","Uneseni ID nije validan");
            return "redirect:/admin/dodaj_frizera_page";
        }

        frizerService.addFrizer(frizerDTO);
        return "redirect:/admin/dodaj_frizera_page";
    }

    @PostMapping("/obrisi_frizera_request")
    public String obrisiFrizera(@Valid @RequestParam Long id) {
        frizerService.deleteFrizer(id);
        return "redirect:/admin/svi_frizeri_page";
    }
}
