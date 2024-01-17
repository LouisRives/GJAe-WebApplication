package Avocado.main_project.Entities.task;

import Avocado.main_project.Entities.activity.ActivityService;
import Avocado.main_project.Entities.criterion.Criterion;
import Avocado.main_project.Entities.solver.Solver;
import Avocado.main_project.Entities.solver.SolverKey;
import Avocado.main_project.Entities.solver.SolverService;
import Avocado.main_project.Entities.user.UserService;
import Avocado.main_project.Entities.workload.Workload;
import Avocado.main_project.Entities.workload.WorkloadService;
import Avocado.main_project.Login.CustomUserDetails;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Controller
public class TaskController {

    private final TaskService taskService;
    private final ActivityService activityService;
    private final SolverService solverService;
    private final UserService userService;
    private final WorkloadService workloadService;

    @Autowired
    public TaskController(TaskService taskService,
                          ActivityService activityService,
                          SolverService solverService,
                          UserService userService,
                          WorkloadService workloadService) {

        this.taskService = taskService;
        this.activityService = activityService;
        this.solverService = solverService;
        this.userService = userService;
        this.workloadService = workloadService;
    }

    @GetMapping("/task/{taskId}")
    public String getCourses(@PathVariable Long taskId,
                             Model model,
                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        Task task = taskService.getTaskById(taskId);
        Long activityId = task.getActivityId();
        String parentActivityName = activityService.getActivityById(activityId).getName();

        List<Solver> solvers = solverService.getSolversByTaskId(task.getId());
        String[][] solverTable = new String[solvers.size()][2];
        int rowCounter = 0;
        for (Solver solver : solvers) {
            Long userId = solver.getId().getUserId();
            String userName = userService.getUserById(userId).getName();
            String mainSolver = "Yes";
            if (!solver.getMainSolver()) {
                mainSolver = "No";
            }
            solverTable[rowCounter][0] = userName;
            solverTable[rowCounter][1] = mainSolver;
            rowCounter++;
        }

        List<Workload> workloads = workloadService.getWorkloadsByTaskId(task.getId());
        String[][] workloadTable = new String[workloads.size()][4];
        rowCounter = 0;
        for (Workload workload : workloads) {
            Long userId = workload.getUserId();
            String userName = userService.getUserById(userId).getName();

            workloadTable[rowCounter][0] = userName;
            workloadTable[rowCounter][1] = workload.getHoursSpent().toString();
            workloadTable[rowCounter][2] = workload.getStudentsDevoted().toString();
            workloadTable[rowCounter][3] = workload.getReport();

            rowCounter++;
        }

        boolean userIsSolver = false;
        boolean userIsMainSolver = false;
        for (Solver solver : solvers) {
            if (Objects.equals(solver.getId().getUserId(), userDetails.getId())) {
                userIsSolver = true;
                if (solver.getMainSolver()) {
                    userIsMainSolver = true;
                }
            }
        }

        model.addAttribute("task", task);
        model.addAttribute("parentActivityName", parentActivityName);
        model.addAttribute("solvers", solverTable);
        model.addAttribute("workloads", workloadTable);
        model.addAttribute("currentUserId", userDetails.getId());
        model.addAttribute("userIsSolver", userIsSolver);
        model.addAttribute("userIsMainSolver", userIsMainSolver);
        model.addAttribute("newWorkload", new Workload());
        model.addAttribute("activityId", activityId);
        model.addAttribute("courseId", activityService.getActivityById(activityId).getCourseId());

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("role", userDetails.getRole());
        return "Task";
    }

    @PostMapping("/handleSolverAction")
    public String handleSolverAction(@RequestParam("currentUserId") Long currentUserId,
                                     @RequestParam("taskId") Long taskId,
                                     @RequestParam("action") String action) {
        SolverKey key = new SolverKey(currentUserId, taskId);
        if ("signUpAsSolver".equals(action)) {
            solverService.createSolver(key, false);
        } else if ("signUpAsMainSolver".equals(action)) {
            List<Solver> solvers = solverService.getSolversByTaskId(taskId);
            for (Solver solver : solvers) {
                if (solver.getMainSolver()) {
                    solverService.updateSolver(solver.getId(), false);
                    break;
                }
            }
            solverService.updateSolver(key, true);
        } else if ("stopBeingSolver".equals(action)) {
            Solver solver = solverService.getSolverBySolverKey(key);
            solverService.deleteSolver(solver);
        }

        return "redirect:/task/" + taskId;
    }

    @PostMapping("/task/{taskId}/addWorkload")
    public String addCriterion(@ModelAttribute("newWorkload") Workload newWorkload,
                               @PathVariable Long taskId,
                               @AuthenticationPrincipal CustomUserDetails userDetails) {
        newWorkload.setTaskId(taskId);
        newWorkload.setUserId(userDetails.getId());
        workloadService.createWorkload(newWorkload);
        return "redirect:/task/" + taskId;
    }

}