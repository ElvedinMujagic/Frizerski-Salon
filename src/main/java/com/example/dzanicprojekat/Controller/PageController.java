package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Services.FrizerService;
import com.example.dzanicprojekat.Services.UserService;
import com.example.dzanicprojekat.Services.UslugeService;
import com.example.dzanicprojekat.Utility.DTOs.RegisterDTO;
import com.example.dzanicprojekat.Utility.DTOs.SessionDTO;
import com.example.dzanicprojekat.Utility.DTOs.UpdateUserDTO;
import com.example.dzanicprojekat.Utility.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    private final UserService userService;
    private final FrizerService frizerService;
    private final UslugeService uslugeService;

    public PageController(UserService userService, FrizerService frizerService, UslugeService uslugeService) {
        this.userService = userService;
        this.frizerService = frizerService;
        this.uslugeService = uslugeService;
    }

    @GetMapping("/")
    public String Home() {
        return "/info/home";
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
    public String Cijene(Model model) {
        model.addAttribute("usluge",uslugeService.getAllActiveUsluga());
        return "/info/Cijene";
    }

    @GetMapping("/general/settings")
    public String settingsProfile(Model model) {
        model.addAttribute("title","Settings");
        model.addAttribute("data",new RegisterDTO());
        return "/dashboard/general/settings";
    }

    @PostMapping("/change_password")
    public String changePassword(
            Authentication auth,
            @RequestParam("new_pass") String new_pass,
            @RequestParam("conf_pass") String conf_pass,
            RedirectAttributes model) {

        User user = userService.getByUsername((String) auth.getPrincipal());
        switch (userService.changePassword(user,new_pass,conf_pass)) {
            case 1:
                model.addFlashAttribute("changed","Šifra je promjenjena!");
                break;
            case 0:
                model.addFlashAttribute("error","Šifre nisu iste!");
                break;
            default:
                model.addFlashAttribute("error","Šifre mora da sadrži barem 6 znakova");
        }

        return "redirect:/general/settings";
    }

    @GetMapping("/general/edit_profile")
    public String editProfile(Model model) {
        model.addAttribute("title","Editing Profile");
        model.addAttribute("data",new UpdateUserDTO());
        return "/dashboard/general/edit_profile";
    }

    @PostMapping("/update_user_profile")
    public String saveUserProfileChanges(@ModelAttribute("data") UpdateUserDTO new_user_data,
                                         Authentication auth,
                                         RedirectAttributes model,
                                         HttpSession session) {
        User curr_user_data = userService.getByUsername((String) auth.getPrincipal());
        System.out.println("PRINCIPLE:"+auth.getPrincipal());
        if(userService.checkByUsername(new_user_data.getUsername())) {
            model.addFlashAttribute("error", "Ime je već zauzeto!");
            return "redirect:/general/edit_profile";
        }

        SessionDTO updated_session = new SessionDTO(userService.updateUser(curr_user_data,new_user_data));
        session.setAttribute("user",updated_session);

        if(curr_user_data.getRole() == Role.FRIZER)
            frizerService.updateFrizer(curr_user_data,new_user_data);
        model.addFlashAttribute("changed","Uspješno promjenjeni podatci profila!");
        return "redirect:/general/edit_profile";
    }
}
