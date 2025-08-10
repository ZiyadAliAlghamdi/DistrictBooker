package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Admin;
import org.example.capstone2.Repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public Admin getSingleAdmin(Integer id){
        Admin admin = adminRepository.getAdminById(id);

        if (admin == null){
            throw new ApiException("<admin-service/getSingleAdmin> admin not found");
        }

        return admin;
    }

    public void addAdmin(Admin admin){
        adminRepository.save(admin);
    }

    public void updateAdmin(Integer id, Admin admin){
        Admin oldAdmin = adminRepository.getAdminById(id);

        if (oldAdmin == null){
            throw new ApiException("<admin-service/updateAdmin> admin not found");
        }

        oldAdmin.setUsername(admin.getUsername());
        oldAdmin.setEmail(admin.getEmail());
        oldAdmin.setPhone(admin.getPhone());
        adminRepository.save(oldAdmin);
    }

    public void deleteAdmin(Integer id){
        Admin admin = adminRepository.getAdminById(id);

        if (admin == null){
            throw new ApiException("<admin-service/deleteAdmin> admin not found");
        }

        adminRepository.delete(admin);
    }
}
