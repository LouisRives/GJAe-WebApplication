package Avocado.main_project.Entities.exportedEvaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="exported-evaluations")
public class ExportedEvaluationController {

    private final ExportedEvaluationService exportedEvaluationService;

    @Autowired
    public ExportedEvaluationController(ExportedEvaluationService exportedEvaluationService) {
        this.exportedEvaluationService = exportedEvaluationService;
    }

    @GetMapping("/test")
    public String testExportedEvaluationController() {
        return "Exported evaluation controller works!";
    }

}
