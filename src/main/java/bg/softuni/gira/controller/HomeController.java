package bg.softuni.gira.controller;

import bg.softuni.gira.config.UserSession;
import bg.softuni.gira.dto.TaskInfoDto;
import bg.softuni.gira.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final TaskService taskService;

    public HomeController(UserSession userSession, TaskService taskService) {
        this.userSession = userSession;
        this.taskService = taskService;
    }


    @GetMapping("/")
    public String nonLoggedIndex() {
        if(userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String loggedInIndex(Model model) {
        if(!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        List<TaskInfoDto> allTasks = taskService.listTasks().stream().map(TaskInfoDto::new).toList();

        model.addAttribute("allTasks", allTasks);

        return "home";
    }
}
