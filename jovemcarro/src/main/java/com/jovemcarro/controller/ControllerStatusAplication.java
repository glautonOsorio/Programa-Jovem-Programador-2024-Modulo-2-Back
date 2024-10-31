package com.jovemcarro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controllerStatus")
public class ControllerStatusAplication {

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }
    @RequestMapping("/ok")
    public String statusOk() {
        return "Ok";
    }

}
