package com.example.prac5up.controllers;

import com.example.prac5up.models.Roles;
import com.example.prac5up.models.User;
import com.example.prac5up.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    private String RegView() {
        return "regis";
    }

    @PostMapping("/registration")
    private String Reg(User user, Model model) {
        User user_from_db = userRepository.findByUsername(user.getUsername());
        if (user_from_db != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Roles.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
