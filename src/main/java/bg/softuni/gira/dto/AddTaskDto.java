package bg.softuni.gira.dto;

import bg.softuni.gira.entity.enums.ClassificationName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
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

}
