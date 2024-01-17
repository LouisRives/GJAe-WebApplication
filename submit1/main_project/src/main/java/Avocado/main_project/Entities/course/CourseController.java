package Avocado.main_project.Entities.course;

import Avocado.main_project.Entities.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Avocado.main_project.Login.CustomUserDetails;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String getCourses(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("newCourse", new Course());

        // Pass userDetails attributes to the model for the navbar
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("role", userDetails.getRole());
        model.addAttribute("email", userDetails.getEmail());

        return "courses";
    }

    @PostMapping("/courses")
    public String addCourse(Course newCourse) {
        courseService.createCourse(newCourse.getName());
        return "redirect:/courses";
    }

    @PostMapping("/courses/delete/{courseId}")
    public String deleteCourse(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId);
        courseService.deleteCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/importStudents")
    public String getImportStudentsView(){
        return "importStudents";
    }

    @GetMapping("/importTeams")
    public String getImportTeamsView(){
        return "importTeams";
    }
}

