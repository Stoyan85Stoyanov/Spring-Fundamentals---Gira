package bg.softuni.gira.dto;

import bg.softuni.gira.entity.Task;
import bg.softuni.gira.entity.User;
import bg.softuni.gira.entity.enums.ClassificationName;
import bg.softuni.gira.entity.enums.Progress;

import java.time.LocalDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
