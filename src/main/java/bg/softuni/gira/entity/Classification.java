package bg.softuni.gira.entity;

import bg.softuni.gira.entity.enums.ClassificationName;
import jakarta.persistence.*;

@Entity
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

    public Classification() {

    }

    public ClassificationName getName() {
        return name;
    }

    public void setName(ClassificationName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
