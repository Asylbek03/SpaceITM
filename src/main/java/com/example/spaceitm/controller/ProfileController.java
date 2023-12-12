package com.example.spaceitm.controller;

import com.example.spaceitm.model.User;
import com.example.spaceitm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }

    @GetMapping("/edit")
    public String editProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "edit-profile";
    }

    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/forum")
    public String redirectToForum() {
        return "redirect:/forum";
    }
}
