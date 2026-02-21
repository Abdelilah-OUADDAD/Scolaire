package com.System.Scolaire.model.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;

import com.System.Scolaire.model.entity.User;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    @NotBlank(message = "Le nom est obligatoire!")
    @Size(min = 2, max = 50, message = "Le nom doit avoir entre 2 et 50 caractères!")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire!")
    @Size(min = 2, max = 50, message = "Le prénom doit avoir entre 2 et 50 caractères!")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire!")
    @Email(message = "L'email n'est pas valide!")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire!")
    @Size(min = 6, message = "Le mot de passe doit avoir au moins 6 caractères!")
    private String password;

    @NotBlank(message = "Le type d'utilisateur est obligatoire!")
    private String type_user;

    private Date date_creation;

    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .type_user(entity.getType_user())
                .date_creation(entity.getDate_creation())
                .build();
    }
}
