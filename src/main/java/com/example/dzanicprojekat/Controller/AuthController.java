package com.example.dzanicprojekat.Controller;

import com.example.dzanicprojekat.Entities.Admin;
import com.example.dzanicprojekat.Entities.Client;
import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Services.AdminService;
import com.example.dzanicprojekat.Services.ClientService;
import com.example.dzanicprojekat.Services.FrizerService;
import com.example.dzanicprojekat.Services.UserService;
import com.example.dzanicprojekat.Utility.DTOs.LoginDTO;
import com.example.dzanicprojekat.Utility.DTOs.RegisterDTO;
import com.example.dzanicprojekat.Utility.DTOs.SessionDTO;
import com.example.dzanicprojekat.Utility.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class AuthController {

    private final UserService userService;
    private final ClientService clientService;
    private final AdminService adminService;
    private final FrizerService frizerService;

    public AuthController(UserService userService, ClientService clientService,
                          AdminService adminService,
                          FrizerService frizerService) {
        this.userService = userService;
        this.clientService = clientService;
        this.adminService = adminService;
        this.frizerService = frizerService;
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> processRegistration(@Valid @ModelAttribute RegisterDTO request, BindingResult bindingResult) {
        System.out.println("ENDPOINT HIT - STARTING REGISTRATION");

        if (clientService.checkByEmail(request.getEmail())) {
            bindingResult.rejectValue("email","email.taken","Email zauzet");
        }

        if(clientService.checkByUsername(request.getUsername())) {
            bindingResult.rejectValue("username","username.taken","Korisnicko ime zauzeto");
        }

        if(!request.validPassword()) {
            bindingResult.rejectValue("confPass","password.not.valid","Passwordi nisu isti");
        }

        if(bindingResult.hasErrors()) {
            Map<String,String> errors = new HashMap<>();
            for(FieldError error: bindingResult.getFieldErrors()) {
                errors.put(error.getField(),error.getDefaultMessage());
            }
            System.out.println(errors);
            return ResponseEntity.badRequest().body(errors);
        }

        Map<String,String> response = new HashMap<>();
        try {
            Client savedClient = clientService.registerClient(request);
            System.out.println("Saved Client ID: " + savedClient.getId() +
                               "\nSaved Client Username: " + savedClient.getUsername());
            response.put("redirect","/success");
            return ResponseEntity.ok(response);
        }  catch (Exception e) {
            response.put("error","Registration Failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> processLogin(@Valid @ModelAttribute LoginDTO request,
                                          HttpServletRequest httpRequest,
                                          HttpSession session
                                          ) {

        System.out.println("ENDPOINT HIT - STARTING LOGIN");
        Map<String,String> response = new HashMap<>();
        if(!userService.validLogin(request,httpRequest)) {
            response.put("error","Invalid login credentials");
            return ResponseEntity.badRequest().body(response);
        }
        // Napraviti try catch block koji omogucava da se ovo odradi bez zvanja DB 3 puta
        // -> Napraviti validLogin da vrati User ili Optional<User>
        SessionDTO<?> sessionDTO;
        System.out.println("CHECKING ROLE OF USER");
        User user = userService.getByUsername(request.getUsername());
        switch (user.getRole()) {
            case ADMIN:
                System.out.println("USER-ROLE: ADMIN");
                Admin admin = adminService.getByUsername(request.getUsername());
                sessionDTO = new SessionDTO<>(admin);
                sessionDTO.fillAdmin(admin);
                break;
            case CLIENT:
                System.out.println("USER-ROLE: CLIENT");
                Client client = clientService.getByUsername(request.getUsername());
                sessionDTO = new SessionDTO<>(client);
                sessionDTO.fillClient(client);
                break;
            case FRIZER:
                System.out.println("USER-ROLE: FRIZER");
                Frizer frizer = frizerService.getByUsername(request.getUsername());
                sessionDTO = new SessionDTO<>(frizer);
                sessionDTO.fillFrizer(frizer);
                break;
            default:
                response.put("error","Error during role validation");
                return ResponseEntity.badRequest().body(response);
        }
        response.put("redirect","/");
        session.setAttribute("user",sessionDTO);
        System.out.println(sessionDTO.getRole());
        return ResponseEntity.ok(response);
    }

}
