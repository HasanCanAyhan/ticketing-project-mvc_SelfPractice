package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;

    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }





    @GetMapping("/create")
    public String createTask(Model model){
        //show all projects
        //show all employees

        model.addAttribute("task", new TaskDTO());

        model.addAttribute("projects" , projectService.findAll());

        model.addAttribute("employees", userService.findEmployees());

        model.addAttribute("tasks", taskService.findAll());

        return "task/create";

    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute("task") TaskDTO task){
        //show all projects
        //show all employees

        task.setId(new Random().nextLong());

        taskService.save(task);


        return "redirect:/task/create";

    }



}
