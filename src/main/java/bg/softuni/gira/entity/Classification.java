package bg.softuni.gira.entity;

import bg.softuni.gira.entity.enums.ClassificationName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "classifications")
public class Classification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private ClassificationName name;


    private String description;

    public Classification(ClassificationName name, String description) {
        this.setName(name);
        this.setDescription(description);
    }
}
