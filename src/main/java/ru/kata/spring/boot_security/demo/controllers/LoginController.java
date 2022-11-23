package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 23.11.2022
 * Time: 19:37
 */
@Controller
@RequestMapping("/")
public class LoginController {


    @GetMapping("/login")
    public String login() {

        return "/login";
    }
}
