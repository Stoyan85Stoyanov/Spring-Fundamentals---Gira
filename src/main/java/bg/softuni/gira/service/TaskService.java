package bg.softuni.gira.service;

import bg.softuni.gira.config.UserSession;
import bg.softuni.gira.dto.AddTaskDto;
import bg.softuni.gira.entity.Classification;
import bg.softuni.gira.entity.Task;
import bg.softuni.gira.entity.User;
import bg.softuni.gira.entity.enums.ClassificationName;
import bg.softuni.gira.entity.enums.Progress;
import bg.softuni.gira.repository.ClassificationRepository;
import bg.softuni.gira.repository.TaskRepository;
import bg.softuni.gira.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private final ClassificationRepository classificationRepository;
    private final TaskRepository taskRepository;
    private final UserSession userSession;
    private final UserRepository userRepository;


    public TaskService(ClassificationRepository classificationRepository, TaskRepository taskRepository, UserSession userSession, UserRepository userRepository) {
        this.classificationRepository = classificationRepository;
        this.taskRepository = taskRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }
    public boolean create(AddTaskDto data) {

        if (!userSession.isLoggedIn()) {
            return false;
        }

        Optional<User> byId = userRepository.findById(userSession.id());

        if (byId.isEmpty()) {
            return false;
        }
        Optional<Classification> byName = classificationRepository.findByName(data.getClassification());

        if (byName.isEmpty()) {
            return false;
        }

        Task task = new Task();
        task.setName(data.getName());
        task.setDescription(data.getDescription());
        task.setDueDate(data.getDueDate());
        task.setClassification(byName.get());
        task.setProgress(Progress.OPEN);
        task.setUser(byId.get());

        if (task.setProgress(Progress.OPEN)) {
            task.setProgress(Progress.OPEN);
        }else if (task.setProgress(Progress.OPEN)) {
            task.setProgress(Progress.IN_PROGRESS);
        } else if (task.setProgress(Progress.IN_PROGRESS)) {
            task.setProgress(Progress.COMPLETED);
        }

        taskRepository.save(task);
        return true;
    }

    public Map<ClassificationName, List<Task>> findAllByClassification() {
        Map<ClassificationName, List<Task>> result = new HashMap<>();

        List<Classification> allClassifications = classificationRepository.findAll();

        for (Classification classification : allClassifications) {
            List<Task> tasks = taskRepository.findAllByClassification(classification.getName());
            result.put(classification.getName(), tasks);
        }
        return result;
    }

    public List<Task> listTasks(){
        return taskRepository.findAllTasks();
    }

    public void delete(Long id) {
        Optional<User> user = userRepository.findById(userSession.id());

        if (user.isEmpty()) {
            return;
        }

        Task mayBeTask = taskRepository.findById(id);

//        if (mayBeTask.isEmpty()) {
//            return;
//        }

        if (mayBeTask.getProgress() == Progress.OPEN) {
            mayBeTask.setProgress(Progress.IN_PROGRESS);
            taskRepository.save(mayBeTask);
        } else if (mayBeTask.getProgress() == Progress.IN_PROGRESS) {
            mayBeTask.setProgress(Progress.COMPLETED);
            taskRepository.save(mayBeTask);
        } else if (mayBeTask.getProgress() == Progress.COMPLETED) {
            taskRepository.delete(mayBeTask);
        }

    }
}


