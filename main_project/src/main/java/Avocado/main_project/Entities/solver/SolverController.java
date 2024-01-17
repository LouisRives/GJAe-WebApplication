package Avocado.main_project.Entities.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="solvers")
public class SolverController {

    private final SolverService solverService;

    @Autowired
    public SolverController(SolverService solverService) {
        this.solverService = solverService;
    }

    @GetMapping("/test")
    public String testSolverController() {
        return "Solver controller works!";
    }

}