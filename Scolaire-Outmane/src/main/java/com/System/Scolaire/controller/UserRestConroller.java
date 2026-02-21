package com.System.Scolaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.System.Scolaire.model.Dto.UserDto;
import com.System.Scolaire.service.UserService;

@RestController
@RequestMapping("/Users")
public class UserRestConroller {

    @Autowired
    private UserService userService;

    @GetMapping("/get_User")
    public UserDto getMethodName(@RequestParam Integer Id) {
        UserDto dto = userService.GetUser(Id);
        return UserDto.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .type_user(dto.getType_user())
                .date_creation(dto.getDate_creation())
                .build();
    }

    @PostMapping("/save")
    public UserDto Save(@RequestBody UserDto empl) {
        System.out.println(empl);
        return userService.SaveUser(empl);
    }

    @GetMapping("/Delete")
    public void Delete(@RequestParam Integer id) {
        userService.DeleteUser(id);
    }

    @PutMapping("/Update")
    public UserDto Update(@RequestBody UserDto employee) {
        return userService.UpdateUser(employee);
    }
}
