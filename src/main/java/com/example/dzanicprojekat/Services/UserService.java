package com.example.dzanicprojekat.Services;

import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Repositories.UserRepo;
import com.example.dzanicprojekat.Utility.DTOs.LoginDTO;
import com.example.dzanicprojekat.Utility.DTOs.RegisterDTO;
import com.example.dzanicprojekat.Utility.DTOs.UpdateUserDTO;
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

    private final FrizerService frizerService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public int changePassword(User user,String new_pass,String conf_pass) {
        if(new_pass.length() < 6 || conf_pass.length() < 6) {
            return -1;
        }

        if(!new_pass.equals(conf_pass)) {
            return 0;
        }
        String nova_sifra = passwordEncoder.encode(new_pass);
        user.setHash(nova_sifra);
        userRepo.save(user);
        return 1;
    }

    public User updateUser(User user,UpdateUserDTO dto){

        if(dto.getUsername() != null && !dto.getUsername().isBlank())
            user.setUsername(dto.getUsername());

        if(dto.getIme() != null && !dto.getIme().isBlank())
            user.setIme(dto.getIme());

        if(dto.getPrezime() != null && !dto.getPrezime().isBlank())
            user.setPrezime(dto.getPrezime());

        if(dto.getSpol() != null && !dto.getSpol().isBlank())
            user.setSpol(dto.getSpol());

        if(dto.getBrojTelefona() != null && !dto.getBrojTelefona().isBlank())
            user.setBrojTelefona(dto.getBrojTelefona());

        userRepo.save(user);
        updateSession(user);
        return user;
    }

    public User addUser(RegisterDTO registerDTO) {
        User newUser = convertToUser(registerDTO);
        return userRepo.save(newUser);
    }

    public List<User> getClientsOnly() {
        List<Role> excludedRoles = List.of(Role.ADMIN,Role.FRIZER);
        return userRepo.findByRoleNotIn(excludedRoles);
    }

    public List<User> getFrizersOnly() {
        List<Role> excludedRoles = List.of(Role.ADMIN,Role.CLIENT);
        return userRepo.findByRoleNotIn(excludedRoles);
    }

    public boolean checkByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public boolean checkByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public User getById(Long id) {
        return userRepo.readById(id);
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

    private void updateSession(User user){
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorityList);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
