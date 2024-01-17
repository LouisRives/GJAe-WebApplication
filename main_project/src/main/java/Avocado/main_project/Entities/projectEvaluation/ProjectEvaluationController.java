package Avocado.main_project.Entities.projectEvaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="project-evaluations")
public class ProjectEvaluationController {

    private final ProjectEvaluationService projectEvaluationService;

    @Autowired
    public ProjectEvaluationController(ProjectEvaluationService projectEvaluationService) {
        this.projectEvaluationService = projectEvaluationService;
    }

    @GetMapping("/test")
    public String testProjectEvaluationController() {
        return "Project evaluation controller works!";
    }

}
