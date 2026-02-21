package com.System.Scolaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.System.Scolaire.model.Dto.UserDto;
import com.System.Scolaire.model.entity.User;
import com.System.Scolaire.service.UserService;
import com.System.Util.DateUtil;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder; // BCryptPasswordEncoder

    // ------------------------------------------

    @GetMapping("/Login")
    public String loginPage() {

        return "Login"; // login.html
    }

    @PostMapping("/LoginP")
    public String login(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        User user = userService.findByEmail(email);

        if (user == null || !user.getType_user().equals("Admin")) {
            model.addAttribute("error", "Email and Password or isn't the Admin!");
            return "Login";
        }

        // ✅ Verify encrypted password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "Password ghalet!");
            return "Login";
        }

        // HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", user);
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userRole", user.getType_user());
        session.setAttribute("isLoggedIn", true);

        session.setMaxInactiveInterval(30 * 60); // 30 minutes

        return "redirect:/UserList";
    }

    @GetMapping("/UserList")
    public String userList(Model model) {
        model.addAttribute("UserList", userService.getAllUser());
        return "UserList";
    }

    @GetMapping("/UserList/UserEdit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        UserDto userDto = userService.GetUser(id);
        model.addAttribute("userEdit", userDto);
        return "UserEdit";
    }

    @PostMapping("/UserList/UserEdit")
    public String updateUser(@Valid @ModelAttribute("userEdit") UserDto userDto,
            BindingResult bindingResult,
            @RequestParam String oldPassword,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        userDto.setDate_creation(DateUtil.getCurrentDateGMT());

        if (bindingResult.hasErrors()) {

            return "UserEdit";
        }

        // Check old password
        if (!userService.checkOldPassword(userDto.getId(), oldPassword)) {
            model.addAttribute("error", "Old password is incorrect!");
            model.addAttribute("userEdit", userDto);
            return "UserEdit";
        }

        // Check new passwords match
        if (!password.isEmpty() && !password.equals(confirmPassword)) {
            model.addAttribute("error", "New passwords do not match!");
            model.addAttribute("userEdit", userDto);
            return "UserEdit";
        }

        // Update password
        if (!password.isEmpty()) {
            userDto.setPassword(password);
        }

        // Save
        userService.UpdateUser(userDto);

        model.addAttribute("success", "Profile updated successfully!");
        model.addAttribute("userEdit", userDto);
        return "UserEdit";
    }

    // ✅ GET - Show form
    @GetMapping("/UserAdd")
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "UserAdd";
    }

    // ✅ POST - Add user
    @PostMapping("/UserAdd/Add")
    public String addUser(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult,
            @RequestParam String confirmPassword,
            Model model) {

        userDto.setDate_creation(DateUtil.getCurrentDateGMT());
        // ✅ Check validation errors
        if (bindingResult.hasErrors()) {

            return "UserAdd";
        }

        // Check email
        if (userService.findByEmail(userDto.getEmail()) != null) {
            model.addAttribute("error", "Email deja kayn!");
            model.addAttribute("user", userDto);
            return "UserAdd";
        }

        // Check password match
        if (!userDto.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            model.addAttribute("user", userDto);
            return "UserAdd";
        }

        // Save
        userService.SaveUser(userDto);

        model.addAttribute("success", "User added successfully!");
        model.addAttribute("user", new UserDto());

        return "UserAdd";
    }

    @GetMapping("/UserList/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.DeleteUser(id);
        return "redirect:/UserList";
    }
}
