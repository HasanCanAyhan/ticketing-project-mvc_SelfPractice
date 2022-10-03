package com.cydeo.controller;

import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {


    @GetMapping("/create")
    public String createTask(Model model){
        //show all projects
        //show all employees


        return "task/create";
    }



}
