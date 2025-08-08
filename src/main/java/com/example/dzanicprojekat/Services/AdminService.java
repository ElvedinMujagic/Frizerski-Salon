package com.example.dzanicprojekat.Services;

import com.example.dzanicprojekat.Entities.Admin;
import com.example.dzanicprojekat.Entities.User;
import com.example.dzanicprojekat.Repositories.AdminRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminService {

    private final AdminRepo adminRepo;


    public Admin registerAdmin(User user, boolean t) {
        Admin admin = new Admin();
        admin.setId(user.getId());
        admin.setSuperAdmin(t);
        adminRepo.save(admin);
        return admin;
    }

    public Admin getById(long id) {
        return adminRepo.readById(id);
    }

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
}
