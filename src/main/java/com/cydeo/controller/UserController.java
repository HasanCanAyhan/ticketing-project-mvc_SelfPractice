package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        //go to create html and provides whatever needs it (user object, roles, users)
        //model.addAttribute("user",new UserDTO());
        //model.addAttribute("roles", roleService.findAll() );


        if (bindingResult.hasErrors()){ // page should not be changed

            model.addAttribute("roles", roleService.findAll());

            model.addAttribute("users", userService.findAll());

            return "/user/create";
            // "redirect:/user/create" if you use that, data will be deleted!

        }

        userService.save(user); // save
        //model.addAttribute("users", userService.findAll());

        //return "/user/create";

        return "redirect:/user/create";
    }




    //update button -->> GetMapping
    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {

        //we need it : user object, roles, users


        model.addAttribute("user", userService.findById(username));

        model.addAttribute("roles", roleService.findAll());


        model.addAttribute("users", userService.findAll());

        return "/user/update";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user){

        //update that user
        userService.update(user);

        return "redirect:/user/create";

    }

    //delete

    //when you put something as Data in the Html like firstName, that is Post

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        userService.deleteById(username);

        return "redirect:/user/create";
    }


}
