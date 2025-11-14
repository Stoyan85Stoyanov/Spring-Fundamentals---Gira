package bg.softuni.gira.dto;

import bg.softuni.gira.entity.Task;
import bg.softuni.gira.entity.User;
import bg.softuni.gira.entity.enums.ClassificationName;
import bg.softuni.gira.entity.enums.Progress;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskInfoDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private String classification;
    private Progress progress;
    private ClassificationName classificationName;
    private User user;

    public TaskInfoDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.classification = String.valueOf(task.getClassification());
        this.progress = task.getProgress();
        this.classificationName = task.getClassification().getName();
        this.user = task.getUser();
    }
}
