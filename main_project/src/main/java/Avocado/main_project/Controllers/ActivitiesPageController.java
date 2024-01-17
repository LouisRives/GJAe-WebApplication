package Avocado.main_project.Controllers;

import Avocado.main_project.Entities.activity.Activity;
import Avocado.main_project.Entities.activity.ActivityService;
import Avocado.main_project.Entities.task.Task;
import Avocado.main_project.Entities.task.TaskService;
import Avocado.main_project.Entities.user.Users;
import Avocado.main_project.Login.CustomUserDetails;
import Avocado.main_project.Entities.user.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ActivitiesPageController {

    private final TaskService taskService;
    private final ActivityService activityService;

    public ActivitiesPageController(TaskService taskService, ActivityService activityService, UserService userService) {
        this.taskService = taskService;
        this.activityService = activityService;
    }

    @GetMapping("/activities/{activityId}")
    public String activitiesPage(@PathVariable Long activityId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // Assuming we have a list of tasks and teams/students
        Activity activity = activityService.getActivityById(activityId);
        model.addAttribute("activityId", activity.getId());
        model.addAttribute("activityName", activity.getName());
        model.addAttribute("tasks", getTasks(activityId));
        // Add model attribute for the new task form
        model.addAttribute("newTask", new Task());

        // Pass userDetails attributes to the model
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("role", userDetails.getRole());
        model.addAttribute("email", userDetails.getEmail());
        model.addAttribute("courseId", activity.getCourseId());
        return "activitiesPage";
    }

    private List<Task> getTasks(Long id) {
        // Implement logic to get a list of tasks
        // Return a list of Task objects
      return taskService.getTasksByActivity(id);
    }

    // Handling the form submission for adding a new task
    @PostMapping("/activities/{activityId}/addTask")
    public String addTask(@PathVariable Long activityId, @ModelAttribute Task newTask) {
        System.out.println("Received activityId: " + activityId);

        // Set the activity ID before saving the task
        newTask.setActivityId(activityId);
        // Implement logic to save the new task
        taskService.createTask(newTask);
        return "redirect:/activities/" + activityId;
    }
    
    @PostMapping("/activities/{activityId}/delete/{taskId}")
    public String deleteTask(@PathVariable Long activityId, @PathVariable Long taskId) {
        // Implement logic to delete the task with the given id
        Task task = taskService.getTaskById(taskId);
        taskService.deleteTask(task);
        return "redirect:/activities/" + activityId;
    }
}

