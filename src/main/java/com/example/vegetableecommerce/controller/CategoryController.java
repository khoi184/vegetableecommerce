package com.example.vegetableecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("title", "Category");

        return "categories";
    }
}
