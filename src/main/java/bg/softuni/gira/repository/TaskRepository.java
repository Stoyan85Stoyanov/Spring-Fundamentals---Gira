package bg.softuni.gira.repository;

import bg.softuni.gira.entity.Task;
import bg.softuni.gira.entity.User;
import bg.softuni.gira.entity.enums.ClassificationName;
import bg.softuni.gira.entity.enums.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, ClassificationName> {

    Optional<Task> findByName(User user);

    List<Task> findAllByClassification(ClassificationName classification);

    @Query("SELECT t FROM Task t")
    List<Task> findAllTasks();

    Optional<Task> findByProgress(Progress progress);

    Task findById(Long id);
}
