package bg.softuni.gira.controller;

import bg.softuni.gira.config.UserSession;
import bg.softuni.gira.dto.AddTaskDto;
import bg.softuni.gira.entity.User;
import bg.softuni.gira.repository.UserRepository;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserSession userSession;
    private final UserService userService;
    private final UserRepository userRepository;

    public TaskController(TaskService taskService, UserSession userSession, UserService userService, UserRepository userRepository) {
        this.taskService = taskService;
        this.userSession = userSession;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("taskData")
    public AddTaskDto TaskData() {
        return new AddTaskDto();
    }

    @GetMapping("/add-task")
    public String addTask() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "add-task";
    }

    @PostMapping("/add-task")
    public String doAddTask(
            @Valid AddTaskDto data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskData", bindingResult);

            return "redirect:/add-task";
        }

        boolean success = taskService.create(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("taskData", data);

            return "redirect:/add-task";
        }
        return "redirect:/home";
    }

//    @GetMapping("/home")
//    public ModelAndView getUserHome() {
//
//        Optional<User> user = userRepository.findById(1L);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }

    // Delete button !!!
    @GetMapping("/add-to-tasks/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/home";
    }
}
