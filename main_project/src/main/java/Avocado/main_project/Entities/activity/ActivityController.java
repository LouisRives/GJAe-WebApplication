package Avocado.main_project.Entities.activity;

import Avocado.main_project.Entities.workload.Workload;
import Avocado.main_project.Login.CustomUserDetails;
import Avocado.main_project.Services.WorkloadDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="activities")
public class ActivityController {

    private final ActivityService activityService;
    private final WorkloadDisplayService workloadDisplayService;


    @Autowired
    public ActivityController(ActivityService activityService,
                              WorkloadDisplayService workloadDisplayService) {
        this.activityService = activityService;
        this.workloadDisplayService = workloadDisplayService;
    }

    @GetMapping("/html-test")
    public ResponseEntity<String> HtmlTest() {
        String htmlContent = "<div><p>This is a HTML snippet.</p></div>";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }

    @GetMapping("/{activityId}/workload-statistics")
    public String viewWorkloadStatistics(@PathVariable Long activityId,
                                         Model model,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        String[][] workloadStatistics = workloadDisplayService.getWorkloadStatisticsForActivity(activityId);

        model.addAttribute("workloadStatistics", workloadStatistics);
        model.addAttribute("activityName", activityService.getActivityById(activityId).getName());
        model.addAttribute("activityId", activityId);
        model.addAttribute("courseId", activityService.getActivityById(activityId).getCourseId());

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("role", userDetails.getRole());

        return "workload-statistics";
    }



}
