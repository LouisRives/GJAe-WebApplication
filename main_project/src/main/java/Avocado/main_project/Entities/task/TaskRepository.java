package Avocado.main_project.Entities.task;

import Avocado.main_project.Entities.activity.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByActivityId(Long activityId);

    void deleteTasksByActivityId(Long activityId);
}
