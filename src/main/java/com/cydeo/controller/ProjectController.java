package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers",userService.findManagers()); // show assigned Manager

        model.addAttribute("projects",projectService.findAll()); // for projectList

        return "/project/create";

    }


    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project) {

        projectService.save(project); // save and update

        return "redirect:/project/create";
    }


    //th:href="@{/student/welcome(id=3)}" : Request Param

    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode){ // getMapping bcs give me the view!

        //complete -> status to complete --> do i have service for that?
        projectService.complete(projectService.findById(projectCode));
        //                       give me the project from DataBse, then change it`s status to COMPLETE

        return "redirect:/project/create";
    }


    //update button -->> GetMapping
    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model) {


        model.addAttribute("project", projectService.findById(projectCode));//to show exact project from DataBase

        model.addAttribute("managers",userService.findManagers());


        model.addAttribute("projects",projectService.findAll());

        return "/project/update";


    }

    @PostMapping("/update") // save button in the form
    public String updateProject(@ModelAttribute("project") ProjectDTO project){

        //update that project
        projectService.update(project);

        return "redirect:/project/create";

    }



}
