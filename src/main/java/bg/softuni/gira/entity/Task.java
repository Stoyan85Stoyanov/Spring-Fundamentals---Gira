package bg.softuni.gira.entity;

import bg.softuni.gira.entity.enums.Progress;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(unique = true)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Progress progress;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne(optional = false)
    private Classification classification;

    @ManyToOne
    private User user;


    public Task() {
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

    public Progress getProgress() {
        return progress;
    }

    public boolean setProgress(Progress progress) {
        this.progress = progress;
        return true;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
