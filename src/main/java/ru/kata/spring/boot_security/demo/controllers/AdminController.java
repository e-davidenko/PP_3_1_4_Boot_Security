package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 16.11.2022
 * Time: 19:27
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserValidator userValidator;

    private final UserService userService;
    @Autowired
    public AdminController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") User user) {
        return "admin/register";
    }

    @PostMapping("/register")
    public String performRegister(@ModelAttribute("user") @Valid User user,
                                  BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/register";
        }
        userService.register(user);
        return "redirect:/admin/users";
    }





    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/users";
    }




    //-----------------------------------------------------------------------------//

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "/admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        System.out.println("Click update");
        if (bindingResult.hasErrors())
            return "/admin/edit";
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }
    //-----------------------------------------------------------------------------//

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/admin/show";
    }


    //-----------------------------------------------------------------------------//


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        System.out.println("Click delete");
        User user = userService.getUser(id);
        userService.removeUser(user);
        return "redirect:/admin/users";
    }
}
