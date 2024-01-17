package Avocado.main_project.Controllers;

import Avocado.main_project.Entities.Student.Student;
import Avocado.main_project.Entities.Student.StudentService;
import Avocado.main_project.Entities.activity.Activity;
import Avocado.main_project.Entities.criterion.Criterion;
import Avocado.main_project.Entities.criterion.CriterionService;
import Avocado.main_project.Entities.projectEvaluation.ProjectEvaluation;
import Avocado.main_project.Entities.projectEvaluation.ProjectEvaluationService;
import Avocado.main_project.Entities.team.Team;
import Avocado.main_project.Entities.team.TeamService;
import Avocado.main_project.Login.CustomUserDetails;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class EvaluationPageController {

    //private final StudentService studentService;
    private final TeamService teamService;

    private final StudentService studentService;
    private final CriterionService criterionService;
    private final ProjectEvaluationService evaluationService;

    public EvaluationPageController(TeamService teamService,
                                    StudentService studentService, CriterionService criterionService, ProjectEvaluationService evaluationService) {
        this.teamService = teamService;
        this.studentService = studentService;
        this.criterionService = criterionService;
        this.evaluationService = evaluationService;
    }

    // Endpoint to display the form for entering evaluations
    @GetMapping("/evaluations/{courseId}")
    public String showEvaluationPage(Model model, @PathVariable Long courseId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Team> teams = teamService.getAllTeamsByCourseId(courseId);
        List<Criterion> criteria = criterionService.getCriteriaByCourseId(courseId);
        List<ProjectEvaluation> evaluations = evaluationService.getProjectEvaluationsByCourseId(courseId);

        model.addAttribute("teams", teams);
        model.addAttribute("criteria", criteria);
        model.addAttribute("courseId", courseId);
        model.addAttribute("evaluations", evaluations);
        
        // Pass userDetails attributes to the model
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("role", userDetails.getRole());
        model.addAttribute("email", userDetails.getEmail());

        return "EvaluationPage";
    }

    @PostMapping("/evaluations/{courseId}")
    public String redirectToForm(@RequestParam String evaluationType,
                                 @RequestParam(required = false) Long teamId,
                                 @RequestParam(required = false) Long criterionId,
                                 @PathVariable Long courseId) {

        if ("team".equals(evaluationType) && teamId != null) {
            return "redirect:/evaluations/" + courseId + "/teamEvaluation?teamId=" + teamId;
        } else if ("criteria".equals(evaluationType) && criterionId != null) {
            return "redirect:/evaluations/" + courseId + "/criteriaEvaluation?criterionId=" + criterionId;
        } else {
            // Handle invalid or missing parameters
            return "redirect:/evaluations/" + courseId;
        }
    }


        // Mapping for Team Evaluation Form
        @GetMapping("evaluations/{courseId}/teamEvaluation")
        public String showTeamEvaluationForm(Model model, @PathVariable Long courseId, @RequestParam Long teamId, @AuthenticationPrincipal CustomUserDetails userDetails){
        Team team = teamService.getTeamById(teamId);
        Set<Student> students = teamService.getStudentsByTeamId(teamId);
            model.addAttribute("team", team);
            model.addAttribute("students", students);
            List<Criterion> criteria = criterionService.getCriteriaByCourseId(courseId);

            model.addAttribute("criteria", criteria);
            model.addAttribute("courseId", courseId);
            model.addAttribute("projectEvaluation", new ProjectEvaluation());
            
            // Pass userDetails attributes to the model
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("name", userDetails.getName());
            model.addAttribute("role", userDetails.getRole());
            model.addAttribute("email", userDetails.getEmail());

            return "TeamEvaluationForm";
        }

        @PostMapping("evaluations/{courseId}/teamEvaluation")
        public String submitTeamEvaluation(@ModelAttribute ProjectEvaluation projectEvaluation, @PathVariable Long courseId) {

            // Get the selected criterion
            Criterion criterion = criterionService.getCriterionById(projectEvaluation.getCriterionId());

            // Check if evaluationMethod is true
            if (criterion != null && criterion.getEvaluationMethod()) {
                // If true, divide points equally among students and submit the form for each student
                Set<Student> students = teamService.getStudentsByTeamId(projectEvaluation.getTeamId());
                int pointsPerStudent = projectEvaluation.getPoints() / students.size();

                for (Student student : students) {
                    evaluationService.createProjectEvaluation(
                            projectEvaluation.getTeamId(),
                            projectEvaluation.getCourseId(),
                            student.getId(),
                            projectEvaluation.getCriterionId(),
                            pointsPerStudent,
                            projectEvaluation.getFeedback()
                    );
                }
            } else {
                // If false, submit the form for the selected student
                evaluationService.createProjectEvaluation(
                        projectEvaluation.getTeamId(),
                        projectEvaluation.getCourseId(),
                        projectEvaluation.getStudentId(),
                        projectEvaluation.getCriterionId(),
                        projectEvaluation.getPoints(),
                        projectEvaluation.getFeedback()
                );
            }

            // Implement your logic for saving team evaluation
            return "redirect:/evaluations/" + courseId + "/teamEvaluation?teamId=" + projectEvaluation.getTeamId();
        }

        // Mapping for Criterion Evaluation Form
        @GetMapping("evaluations/{courseId}/criteriaEvaluation")
        public String showCriterionEvaluationForm(Model model, @PathVariable Long courseId, @RequestParam Long criterionId, @AuthenticationPrincipal CustomUserDetails userDetails) {
            Criterion criterion = criterionService.getCriterionById(criterionId);
            List<Team> teams = teamService.getAllTeamsByCourseId(courseId);
            model.addAttribute("teams", teams);
            model.addAttribute("criterion", criterion);
            model.addAttribute("courseId", courseId);
            model.addAttribute("projectEvaluation", new ProjectEvaluation());

            // Pass userDetails attributes to the model
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("name", userDetails.getName());
            model.addAttribute("role", userDetails.getRole());
            model.addAttribute("email", userDetails.getEmail());
            return "criteriaEvaluationForm";
        }

        @PostMapping("evaluations/{courseId}/criteriaEvaluation")
        public String submitCriterionEvaluation(@ModelAttribute ProjectEvaluation projectEvaluation, @PathVariable Long courseId) {
            // Get the selected criterion
            Criterion criterion = criterionService.getCriterionById(projectEvaluation.getCriterionId());

            // Check if evaluationMethod is true
            if (criterion != null && criterion.getEvaluationMethod()) {
                // If true, divide points equally among students and submit the form for each student
                Set<Student> students = teamService.getStudentsByTeamId(projectEvaluation.getTeamId());
                int pointsPerStudent = projectEvaluation.getPoints() / students.size();

                for (Student student : students) {
                    evaluationService.createProjectEvaluation(
                            projectEvaluation.getTeamId(),
                            projectEvaluation.getCourseId(),
                            student.getId(),
                            projectEvaluation.getCriterionId(),
                            pointsPerStudent,
                            projectEvaluation.getFeedback()
                    );
                }
            } else {
                // If false, submit the form for the selected student
                evaluationService.createProjectEvaluation(
                        projectEvaluation.getTeamId(),
                        projectEvaluation.getCourseId(),
                        projectEvaluation.getStudentId(),
                        projectEvaluation.getCriterionId(),
                        projectEvaluation.getPoints(),
                        projectEvaluation.getFeedback()
                );
            }
            return "redirect:/evaluations/" + courseId + "/criteriaEvaluation?criterionId=" + projectEvaluation.getCriterionId();
        }

    @PostMapping("/evaluations/delete/{evaluationId}")
    public String deleteActivity(@PathVariable Long evaluationId) {
        // Implement logic to delete the evaluation with the given id
        ProjectEvaluation evaluation = evaluationService.getProjectEvaluationById(evaluationId);
        evaluationService.deleteProjectEvaluation(evaluation);
        return "redirect:/evaluations/" + evaluation.getCourseId();
    }


    @GetMapping("/fetchStudents")
    public ResponseEntity<Set<Student>> fetchStudents(@RequestParam Long teamId) {
        Set<Student> students = teamService.getStudentsByTeamId(teamId);
        return ResponseEntity.ok(students);
    }

}
