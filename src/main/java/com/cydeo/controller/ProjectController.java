package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public String insertProject(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("managers", userService.findManagers());
            model.addAttribute("projects", projectService.findAll());

            return "/project/create";

        }


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

        return "redirect:/project/create";
    }


    //update button -->> GetMapping
    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model) {


        model.addAttribute("project", projectService.findById(projectCode));

        model.addAttribute("managers",userService.findManagers());


        model.addAttribute("projects",projectService.findAll());

        return "/project/update";


    }

    @PostMapping("/update") // save
    public String updateProject(@Valid @ModelAttribute("project") ProjectDTO project,BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()) {

            model.addAttribute("managers", userService.findManagers());
            model.addAttribute("projects", projectService.findAll());

            return "/project/update";

        }

        //update that project
        projectService.update(project);

        return "redirect:/project/create";

    }


    //project status

    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model){

        //view need project attribute whoever login the application
        //we should see specific manager
        //we picked one manager
        //                                          unique thing : username
        UserDTO manager = userService.findById("john@cydeo.com"); // in th reality Security it comes the from login page


        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);

        model.addAttribute("projects",projects);

        return "/manager/project-status";

    }

    @GetMapping("/manager/complete/{projectCode}") // inside project status complete button
    public String managerCompleteProjectStatus(@PathVariable("projectCode") String projectCode){

        //complete -> status to complete --> do i have service for that?
        projectService.complete(projectService.findById(projectCode));

        return "redirect:/manager/project-status";
    }


}
