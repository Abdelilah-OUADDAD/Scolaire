package com.System.Scolaire.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.System.Scolaire.model.Dto.UserDto;
import com.System.Scolaire.model.entity.User;
import com.System.Scolaire.repository.UserRepo;

import lombok.*;
import java.util.*;

@Service
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements UserServiceInter{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // -----------------------------

    public UserDto GetUser(Integer Id) {
        Optional<User> em = this.userRepo.findById(Id);
        if (em.isPresent())
            return UserDto.toDto(em.get());
        else
            return null;
    }

    public UserDto SaveUser(UserDto user) {
        String password = user.getPassword();

        // ✅ Check if already encrypted
        if (!password.startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(password));
        }
        // if already encrypted, keep her the same thing

        return UserDto.toDto(this.userRepo.save(User.toEntity(user)));
    }

    public void DeleteUser(Integer id) {
        userRepo.deleteById(id);
    }

    public UserDto UpdateUser(UserDto user) {

        User existingUser = userRepo.findById(user.getId()).orElseThrow();

        // Update fields
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setEmail(user.getEmail());
        existingUser.setType_user(user.getType_user());
        existingUser.setDate_creation(user.getDate_creation());

        String newPassword = user.getPassword();
        if (newPassword != null && !newPassword.startsWith("$2a$")) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
        }

        return UserDto.toDto(userRepo.save(existingUser));
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public boolean checkOldPassword(Integer Id, String oldPassword) {
        User user = userRepository.findById(Id).orElseThrow();

        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
