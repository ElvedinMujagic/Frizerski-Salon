package com.example.dzanicprojekat.Services;

import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Entities.Termin;
import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Entities.Usluga;
import com.example.dzanicprojekat.Repositories.TerminRepo;
import com.example.dzanicprojekat.Utility.DTOs.TerminDTO;
import com.example.dzanicprojekat.Utility.State;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TerminService {

    private final TerminRepo terminRepo;
    private final UserService userService;
    private final FrizerService frizerService;
    private final UslugeService uslugeService;

    public TerminService(TerminRepo terminRepo, UserService userService, FrizerService frizerService, UslugeService uslugeService) {
        this.terminRepo = terminRepo;
        this.userService = userService;
        this.frizerService = frizerService;
        this.uslugeService = uslugeService;
    }

    public List<Termin> getAllTermins() {
        return terminRepo.findAll();
    }

    public List<Termin> getAllFrizerTermins(String frizer_name,State state) {
        User user =  userService.getByUsername(frizer_name);
        return terminRepo.findAllByFrizerAndState(frizerService.getById(user.getId()),state);
    }

    public List<Termin> getAllUserTermins(String username) {
        User user =  userService.getByUsername(username);
        return terminRepo.findAllByUser(user);
    }

    public void cancelTermin(TerminDTO terminDTO) {
        Termin termin = terminRepo.findById(terminDTO.getTerminId())
                .orElseThrow(() -> new RuntimeException("ID Termina ne nalazi se u Bazi Podataka"));
        termin.setState(State.Canceled);
        termin.setCancellation_reason(terminDTO.getCancellation_reason());
        terminRepo.save(termin);
    }

    public void potvrdiTermin(TerminDTO terminDTO) {
        Termin termin = terminRepo.findById(terminDTO.getTerminId())
                .orElseThrow(() -> new RuntimeException("ID Termina ne nalazi se u Bazi Podataka"));
        termin.setState(State.Confirmed);
        termin.setPocetak_termina(terminDTO.getTime());
        terminRepo.save(termin);
    }

    public void addTermin(TerminDTO terminDTO) {
        User user = userService.getById(terminDTO.getUserId());
        Frizer frizer = frizerService.getById(terminDTO.getFrizerId());
        Usluga usluga = uslugeService.getById(terminDTO.getUslugeId());
        List<Usluga> uslugaList = new ArrayList<>();
        uslugaList.add(usluga);
        Termin termin = new Termin();
        termin.setFrizer(frizer);
        termin.setUser(user);
        termin.setUsluge(uslugaList);
        termin.setCijena(usluga.getCijena());
        termin.setDatum_termina(terminDTO.getDatum());
        terminRepo.save(termin);
    }

    public void deleteTermin(Long id) {
        terminRepo.deleteById(id);
    }

}
