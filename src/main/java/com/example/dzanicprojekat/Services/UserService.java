package com.example.dzanicprojekat.Services;

import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Repositories.UserRepo;
import com.example.dzanicprojekat.Utility.DTOs.LoginDTO;
import com.example.dzanicprojekat.Utility.DTOs.RegisterDTO;
import com.example.dzanicprojekat.Utility.Role;
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
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(RegisterDTO registerDTO) {
        User newUser = convertToUser(registerDTO);
        return userRepo.save(newUser);
    }

    public List<User> getUsersOnly() {
        List<Role> excludedRoles = List.of(Role.ADMIN,Role.FRIZER);
        return userRepo.findByRoleNotIn(excludedRoles);
    }

    public boolean checkByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public boolean checkByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public User getByUsername(String username) {
        return userRepo.readByUsername(username);
    }

    public boolean validLogin(LoginDTO request, HttpServletRequest httpRequest) {
        User user = userRepo.readByUsername(request.getUsername());
        String password = request.getPassword();
        if (user != null && passwordEncoder.matches(password, user.getHash())) {

            List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority("ROLE_"+ user.getRole()));
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
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


    private User convertToUser(RegisterDTO request) {
        User user = new User();
        user.setIme(request.getIme());
        user.setPrezime(request.getPrezime());
        user.setEmail(request.getEmail());
        user.setHash(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setSpol(request.getSpol());
        user.setBrojTelefona("");
        return user;
    }
}
