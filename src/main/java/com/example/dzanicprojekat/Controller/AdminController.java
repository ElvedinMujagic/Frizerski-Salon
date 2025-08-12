package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Services.AdminService;
import com.example.dzanicprojekat.Services.FrizerService;
import com.example.dzanicprojekat.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    private final FrizerService frizerService;

    public AdminController(UserService userService, AdminService adminService, FrizerService frizerService) {
        this.userService = userService;
        this.adminService = adminService;
        this.frizerService = frizerService;
    }

    @GetMapping("/admin/dodaj_frizera")
    public String dodajFrizera(Model model) {
        model.addAttribute("title","Dodaj Frizera");
        return "/dashboard/admin/dodaj_frizera";
    }
}
