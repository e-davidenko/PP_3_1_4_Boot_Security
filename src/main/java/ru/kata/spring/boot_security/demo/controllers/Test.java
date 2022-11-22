package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 18.11.2022
 * Time: 23:07
 */
@Controller
@RequestMapping("/test")
public class Test {

    private final UserService userService;
@Autowired
    public Test(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/wtf")
    public String get(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getCurrentUser().get());
        return "index";
    }


    @GetMapping("/")
    public String testPage(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("current_user", userService.getCurrentUser().get());
    return "new_pages/admin";
    }

    @GetMapping("/learn")
    public String learnPage(Model model) {

    return "new_pages/learn";
    }
}
