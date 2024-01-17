package Avocado.main_project.Entities.workload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="workloads")
public class WorkloadController {

    private final WorkloadService workloadService;

    @Autowired
    public WorkloadController( WorkloadService workloadService) {
        this.workloadService = workloadService;
    }

    @GetMapping("/test")
    public String testWorkloadController() {
        return "Workload controller works!";
    }

}
