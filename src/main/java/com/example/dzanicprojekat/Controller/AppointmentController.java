package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Utility.DTOs.AppointmentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppointmentController {

    @GetMapping("/appointments")
    public String showForm(Model model) {
        model.addAttribute("appointment", new AppointmentDTO());
        return "appointment-form";
    }

    @PostMapping("/appointments")
    public String submitForm(@ModelAttribute AppointmentDTO appointment) {

        System.out.println("New Appointment: " + appointment.getFullName());
        return "redirect:/appointments/success";
    }
}