package com.example.teste.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class ControllerLogin {

    @GetMapping
    public String abrirTelaLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String abrirHome() {
        return "home";
    }

}
