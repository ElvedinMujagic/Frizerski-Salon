package com.example.dzanicprojekat.Services;


import com.example.dzanicprojekat.Entities.Frizer;
import com.example.dzanicprojekat.Repositories.FrizerRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FrizerService {
    private final FrizerRepo frizerRepo;

    public Frizer getByUsername(String username) {
        return frizerRepo.readByUsername(username);
    }
    public FrizerService(FrizerRepo frizerRepo) {
        this.frizerRepo = frizerRepo;
    }
}
