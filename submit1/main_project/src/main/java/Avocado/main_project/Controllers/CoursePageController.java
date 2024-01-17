package Avocado.main_project.Controllers;


import Avocado.main_project.Entities.activity.Activity;
import Avocado.main_project.Entities.activity.ActivityService;
import Avocado.main_project.Entities.course.Course;
import Avocado.main_project.Entities.course.CourseService;
import Avocado.main_project.Login.CustomUserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CoursePageController {
    private final CourseService courseService;
    private final ActivityService activityService;

    @Autowired
    public CoursePageController(CourseService courseService, ActivityService activityService) {
        this.courseService = courseService;
        this.activityService = activityService;
    }

    @GetMapping("/course/{id}")
    public String getCourse(@PathVariable Long id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // Assuming you have a list of activities
        Course course = courseService.getCourseById(id);
        model.addAttribute("courseId", course.getId());
        model.addAttribute("courseName", course.getName());
        model.addAttribute("activities", getActivities(id));
        model.addAttribute("newActivity", new Activity());

        // Pass userDetails attributes to the model
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("role", userDetails.getRole());
        model.addAttribute("email", userDetails.getEmail());

        return "coursePage";
    }

    private List<Activity> getActivities(Long id) {
        // Implement logic to get a list of activities
        // For example, you can retrieve them from a service or database
        // Return a list of Activity objects
        return activityService.getActivitiesByCourse(id);
    }

    @PostMapping("/course/{courseId}/add")
    public String addActivity(@PathVariable Long courseId, @ModelAttribute Activity newActivity) {
        // Set the course ID before saving the activity
        newActivity.setCourseId(courseId);
        activityService.createActivity(newActivity.getName(), newActivity.getCourseId());
        return "redirect:/course/" + courseId;  // Update the redirect URL
    }


    @PostMapping("/course/{courseId}/delete/{activityId}")
    public String deleteActivity(@PathVariable Long activityId, @PathVariable Long courseId) {
        // Implement logic to delete the activity with the given id
        Activity activity = activityService.getActivityById(activityId);
        activityService.deleteActivity(activity);
        return "redirect:/course/" + courseId;
    }



}