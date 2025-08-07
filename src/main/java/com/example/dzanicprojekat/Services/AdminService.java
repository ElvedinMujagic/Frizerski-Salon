package com.example.dzanicprojekat.Services;

import com.example.dzanicprojekat.Entities.Admin;
import com.example.dzanicprojekat.Repositories.AdminRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminService {

    private final AdminRepo adminRepo;


    public Admin getByUsername(String username) {
        return adminRepo.readByUsername(username);
    }

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
}
