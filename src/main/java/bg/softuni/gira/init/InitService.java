package bg.softuni.gira.init;


import bg.softuni.gira.entity.Classification;
import bg.softuni.gira.entity.enums.ClassificationName;
import bg.softuni.gira.repository.ClassificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner{

    private final Map<ClassificationName, String> description = Map.of(
            ClassificationName.BUG, "Description for BUG",
            ClassificationName.FEATURE, "Description for FEATURE",
            ClassificationName.SUPPORT, "Description for SUPPORT",
            ClassificationName.OTHER, "Description for OTHER" );


    private final ClassificationRepository classificationRepository;

    public InitService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long count = this.classificationRepository.count();

        if (count > 0) {
            return;
        }
        List<Classification> toInsert = Arrays.stream(ClassificationName.values())
                .map(name -> new Classification(name, description.get(name))).toList();

        this.classificationRepository.saveAll(toInsert);
    }
}
