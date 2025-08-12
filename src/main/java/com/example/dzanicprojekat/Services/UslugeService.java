package com.example.dzanicprojekat.Services;

import com.example.dzanicprojekat.Entities.Usluga;
import com.example.dzanicprojekat.Repositories.UslugeRepo;
import com.example.dzanicprojekat.Utility.DTOs.UslugaDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UslugeService {
    private final UslugeRepo uslugeRepo;


    public UslugeService(UslugeRepo uslugeRepo) {
        this.uslugeRepo = uslugeRepo;
    }

    public List<Usluga> getAllUsluga() {
        return uslugeRepo.findAll();
    }


    public void addUsluga(UslugaDTO uslugaDTO) {
        uslugeRepo.save(convertToUsluga(uslugaDTO));
    }

    public boolean checkByNaziv(String naziv) {
        return uslugeRepo.existsByNaziv(naziv);
    }

    private Usluga convertToUsluga(UslugaDTO uslugaDTO) {
        Usluga usluga = new Usluga();
        usluga.setNaziv(uslugaDTO.getNaziv());
        usluga.setCijena(uslugaDTO.getCijena());
        usluga.setTrajanje(uslugaDTO.getTrajanje());
        usluga.setActive(uslugaDTO.isActive());
        return  usluga;
    }
}
