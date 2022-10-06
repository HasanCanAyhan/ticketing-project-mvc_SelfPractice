package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String insertTask(@Valid  @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){
        //show all projects
        //show all employees

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("employees", userService.findEmployees());
            model.addAttribute("tasks", taskService.findAll());

            return "/task/create";

        }

        taskService.save(task);


        return "redirect:/task/create";

    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){

        taskService.deleteById(id);

        return "redirect:/task/create";
    }


    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId ,Model model){

        //whatever view needs

        //except empty task object - empty form
        model.addAttribute("task",taskService.findById(taskId));
        model.addAttribute("projects" , projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());


        return "/task/update";
    }

    /*
    @PostMapping("/update/{taskId}")
    public String updateTask(@PathVariable("taskId") Long taskId,TaskDTO task){

        task.setId(taskId);

        taskService.update(task);



        return "redirect:/task/create";
    }

     */


    @PostMapping("/update/{id}")
    public String updateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("employees", userService.findEmployees());
            model.addAttribute("tasks", taskService.findAll());

            return "/task/update";

        }


        taskService.update(task);

        return "redirect:/task/create";
    }



    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model){ // task which not completed

        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));

        return "/task/pending-tasks";
    }

    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model){ // task which completed

        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETE));

        return "/task/archive";
    }


    @GetMapping("/employee/edit/{id}") // update button inside the pending task
    public String employeeEditTask(@PathVariable Long id,Model model){

        model.addAttribute("task", taskService.findById(id));

        //model.addAttribute("projects", projectService.findAll());
        //model.addAttribute("employees", userService.findEmployees());



        model.addAttribute("statuses", Status.values());

        model.addAttribute("tasks",taskService.findAllTasksByStatusIsNot(Status.COMPLETE));

        return "/task/status-update";
    }

    // employee/update/{id}

    @PostMapping("/employee/update/{id}") // save button inside status-update html
    public String employeeUpdateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("statuses", Status.values());
            model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));

            return "/task/status-update";

        }


        taskService.updateStatus(task); // another logic because status should be new selected

        return "redirect:/task/employee/pending-tasks";


    }





}
