package org.bookzone.core.controller;

import org.bookzone.core.CheckUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private CheckUser checkUser;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("nameOfUser",checkUser.getUsername());
        return "login";
    }

    @GetMapping("/error")
    public String error(Model model){
        model.addAttribute("nameOfUser",checkUser.getUsername());
        return "error";
    }
}
