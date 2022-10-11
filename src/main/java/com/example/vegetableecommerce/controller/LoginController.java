package com.example.vegetableecommerce.controller;

import com.example.vegetableecommerce.dto.SignUpDto;
import com.example.vegetableecommerce.entity.User;
import com.example.vegetableecommerce.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admin/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("adminDto", new SignUpDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAccount(@ModelAttribute("adminDto") SignUpDto signUpDto,
                                BindingResult bindingResult,
                                Model model,
                                HttpSession session) {
        try {
            session.removeAttribute("message");
            if (bindingResult.hasErrors()) {
                model.addAttribute("adminDto", new SignUpDto());
                return "register";
            }

            String username = signUpDto.getUsername();
            User user = userService.findByUsername(username);
            if (user != null) {
                model.addAttribute("adminDto", signUpDto);
                session.setAttribute("message", "Your username has been registered!");
                return "register";
            }
            if (signUpDto.getPassword().equals(signUpDto.getRepeatPassword())){
                userService.registerUser(signUpDto);
                session.setAttribute("message", "Register successfully!");
                model.addAttribute("adminDto", signUpDto);
                return "redirect:/login";
            } else {
                model.addAttribute("adminDto", signUpDto);
                session.setAttribute("message", "Password is not same!");
                return "register";
            }
        } catch (Exception e) {
            session.setAttribute("message", "Opps! Something went wrong!");
        }
        return "register";
    }
}