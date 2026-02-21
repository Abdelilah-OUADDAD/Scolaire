package com.System.Scolaire.model.entity;

import java.util.Date;

import com.System.Scolaire.model.Dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String type_user;
    private Date date_creation;

    public static User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .type_user(dto.getType_user())
                .date_creation(dto.getDate_creation())
                .build();
    }

}
