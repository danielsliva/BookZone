package org.bookzone.core.controller;

import org.bookzone.core.CheckUser;
import org.bookzone.core.model.User;
import org.bookzone.core.service.UserService;
import org.bookzone.core.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckUser checkUser;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("nameOfUser",checkUser.getUsername());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result,Model model){
        User existing = userService.findByEmail(userDto.getEmail());
        model.addAttribute("nameOfUser",checkUser.getUsername());
        if(existing != null ){
            result.rejectValue("email",null,"There is already an account registered with that email");
        }
        if(result.hasErrors()){
            return "registration";
        }
        userService.save(userDto);
        return "redirect:/registration?success";
    }
}
