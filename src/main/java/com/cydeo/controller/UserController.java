package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create") // end point -->> /user/create show the page
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());

        model.addAttribute("roles", roleService.findAll()); // all roles will come from DataBase

        model.addAttribute("users", userService.findAll());

        return "/user/create";
        // what ever inside the view, you have to create data for that
        //what ever view needs (object,list etc) - you need to provide it
    }


    //save button -->> post action --->> then we have user object from the UI , then we are holding it with ModelAttribute
    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user) {

        //go to create html and provides whatever needs it (user object, roles, users)
        //model.addAttribute("user",new UserDTO());
        //model.addAttribute("roles", roleService.findAll() );

        userService.save(user); // save and update
        //model.addAttribute("users", userService.findAll());

        //return "/user/create";

        return "redirect:/user/create";
    }


    /*
    //update button -->> GetMapping
    @GetMapping("/update/{username}")
    public String editUser( @PathVariable("username") String username,Model model){
        //we need it : user object, roles, users

        //user object ${user}
        model.addAttribute("user", userService.findById(username));
        //roles ${user}
        model.addAttribute("roles", roleService.findAll() );

        //users ${users}
        model.addAttribute("users", userService.findAll());



        return "/user/update";
        //to determine what it is inside the view

     */

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", userService.findById(username));

        model.addAttribute("roles", roleService.findAll());


        model.addAttribute("users", userService.findAll());

        return "/user/update";


    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user){

        //update that user
        userService.update(user);

        return "redirect:/user/create";

    }




}
