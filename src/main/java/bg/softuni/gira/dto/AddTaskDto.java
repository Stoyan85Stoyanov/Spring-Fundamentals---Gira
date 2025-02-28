package bg.softuni.gira.dto;

import bg.softuni.gira.entity.enums.ClassificationName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddTaskDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String description;


    private LocalDate dueDate;

    @NotNull
    private ClassificationName classification;

    public AddTaskDto() {
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

    public ClassificationName getClassification() {
        return classification;
    }

    public void setClassification( ClassificationName classification) {
        this.classification = classification;
    }
}
