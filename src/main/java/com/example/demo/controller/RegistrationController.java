package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.addAttribute("message", "User exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);

        return "redirect:/login";
    }


}
