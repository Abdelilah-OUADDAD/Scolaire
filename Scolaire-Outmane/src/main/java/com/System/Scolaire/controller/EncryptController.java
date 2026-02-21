package com.System.Scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.System.Scolaire.model.entity.User;
import com.System.Scolaire.repository.UserRepo;

@RestController
@RequestMapping("/admin")
public class EncryptController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Run once, changes all passwords
    @GetMapping("/encrypt-passwords")
    @PreAuthorize("hasRole('ADMIN')") // Khass admin
    public String encryptAllPasswords() {
        List<User> users = userRepo.findAll();
        int count = 0;

        for (User user : users) {
            String currentPassword = user.getPassword();

            // Check if already encrypted
            if (!currentPassword.startsWith("$2a$")) {
                String encrypted = passwordEncoder.encode(currentPassword);
                user.setPassword(encrypted);
                userRepo.save(user);
                count++;
            }
        }

        return count + " passwords encrypted successfully! Total users: " + users.size();
    }

    // ✅ Encrypt user one with ID
    @GetMapping("/encrypt-user/{id}")
    public String encryptUser(@PathVariable Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User ma kaynch!"));

        if (user.getPassword().startsWith("$2a$")) {
            return "Password deja encrypted!";
        }

        String encrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(encrypted);
        userRepo.save(user);

        return "Password encrypted for: " + user.getEmail();
    }
}
