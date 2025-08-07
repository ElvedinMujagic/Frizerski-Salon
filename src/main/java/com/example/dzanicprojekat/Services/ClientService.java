package com.example.dzanicprojekat.Services;

import com.example.dzanicprojekat.Entities.Client;
import com.example.dzanicprojekat.Repositories.ClientRepo;
import com.example.dzanicprojekat.Utility.DTOs.LoginDTO;
import com.example.dzanicprojekat.Utility.DTOs.RegisterDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;


    public Client registerClient(RegisterDTO request) {
        Client newClient = convertToClient(request);
        return clientRepo.save(newClient);
    }

    public boolean checkByUsername(String username) {
        return clientRepo.existsByUsername(username);
    }

    public boolean checkByEmail(String email) {
        return clientRepo.existsByEmail(email);
    }

    public Client getByUsername(String username) {
        return clientRepo.readByUsername(username);
    }

/*
    public boolean validLogin(LoginDTO request, HttpServletRequest httpRequest) {
        Client client = clientRepo.readByUsername(request.getUsername());
        String password = request.getPassword();
        if (client != null && passwordEncoder.matches(password, client.getHash())) {

            List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority("ROLE_"+ client.getRole()));
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    client.getUsername(),
                    null,
                    authorityList);

            // Ovo odobrava ulaz na endpoint tj. Server zna da se korisnik prijavio
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Bez ovoga server pamti da je prijavljen samo do prvog redirecta
            // Tj: Kada se ode na drugi page stranice Server zaboravlja da je korisnik prijavljen.
            httpRequest.getSession(true).setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );

            System.out.println("Created Authentication Token");
            return true;
        }
        System.out.println("Failed to create Authentication Token - Check Login credentials");
        return false;
    }
*/
    private Client convertToClient(RegisterDTO request) {
        Client client = new Client();
        client.setIme(request.getIme());
        client.setPrezime(request.getPrezime());
        client.setEmail(request.getEmail());
        client.setHash(passwordEncoder.encode(request.getPassword()));
        client.setUsername(request.getUsername());
        client.setSpol(request.getSpol());
        client.setLoyaltyPoints(0);
        client.setNotes("");
        client.setBrojTelefona("");
        return client;
    }



}
