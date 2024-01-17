package Avocado.main_project.Entities.criterion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="c")
public class CriterionController {

    private final CriterionService criterionService;

    @Autowired
    public CriterionController(CriterionService criterionService) {
        this.criterionService = criterionService;
    }

    @GetMapping("/test")
    public String testCriterionController() {
        return "Criterion controller works!";
    }

}
