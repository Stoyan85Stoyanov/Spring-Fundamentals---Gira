package bg.softuni.gira.entity;

import bg.softuni.gira.entity.enums.Progress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
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

    public boolean setProgress(Progress progress) {
        this.progress = progress;
        return true;
    }
}
