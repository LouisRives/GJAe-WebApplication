package Avocado.main_project.Entities.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(Long activityId,
                           Integer order,
                           String name,
                           String type,
                           String description,
                           LocalDate startDate,
                           LocalDate endDate) {
        Task task = new Task();

        task.setActivityId(activityId);
        task.setOrder(order);
        task.setName(name);
        task.setType(type);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setEndDate(endDate);

        return taskRepository.save(task);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(long id){
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByActivity(Long id) {
        return taskRepository.findByActivityId(id);
    }

    public Task updateTask(Long taskId,
                           Long activityId,
                           Integer order,
                           String name,
                           String type,
                           String description,
                           LocalDate startDate,
                           LocalDate endDate) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setActivityId(activityId);
            task.setOrder(order);
            task.setName(name);
            task.setType(type);
            task.setDescription(description);
            task.setStartDate(startDate);
            task.setEndDate(endDate);

            taskRepository.save(task);
            return task;
        }
        return null;
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
    @Transactional
    public void deleteTasksByActivityId(Long activityId) {
        taskRepository.deleteTasksByActivityId(activityId);
    }

}
