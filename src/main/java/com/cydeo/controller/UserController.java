package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user", new UserDTO());

        model.addAttribute("roles", roleService.findAll() ); // all roles will come from DataBase

        model.addAttribute("users", userService.findAll());

        return "/user/create";
        // what ever inside the view, you have to create data for that
        //what ever view needs (object,list etc) - you need to provide it
    }


    //save button -->> post action --->> then we have user object from the UI , then we are holding it with ModelAttribute
    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user , Model model){

        //go to create html and provides whatever needs it (user object, roles, users)
        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles", roleService.findAll() );

        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "/user/create";
    }


}
