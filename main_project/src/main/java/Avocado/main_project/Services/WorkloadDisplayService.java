package Avocado.main_project.Services;

import Avocado.main_project.Entities.task.Task;
import Avocado.main_project.Entities.task.TaskService;
import Avocado.main_project.Entities.user.Users;
import Avocado.main_project.Entities.user.UserService;
import Avocado.main_project.Entities.workload.Workload;
import Avocado.main_project.Entities.workload.WorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WorkloadDisplayService {

    private final TaskService taskService;
    private final WorkloadService workloadService;
    private final UserService userService;

    @Autowired
    public WorkloadDisplayService(TaskService taskService, WorkloadService workloadService, UserService userService) {
        this.taskService = taskService;
        this.workloadService = workloadService;
        this.userService = userService;
    }

    public String[][] getWorkloadStatisticsForActivity(Long activityId) {
        List<Task> tasks = taskService.getTasksByActivity(activityId);
        List<Workload> workloads = new ArrayList<>();

        for (Task task : tasks) {
            Long taskId = task.getId();
            List<Workload> taskWorkloads = workloadService.getWorkloadsByTaskId(taskId);
            workloads.addAll(taskWorkloads);
        }

        HashMap<Long, Integer> userHours = new HashMap<>();
        Integer totalHours = 0;
        for (Workload workload : workloads) {
            Long userId = workload.getUserId();
            Integer hoursSpent = workload.getHoursSpent();

            totalHours += hoursSpent;
            if (userHours.containsKey(userId)) {
                userHours.put(userId, userHours.get(userId) + hoursSpent);
            }
            else {
                userHours.put(userId, hoursSpent);
            }
        }

        int statisticsCounter = 0;
        int statisticsSize = userHours.keySet().size() + 1; // +1 for total in the end
        String[][] statistics = new String[statisticsSize][3];
        for (Long userId : userHours.keySet()) {
            Users user = userService.getUserById(userId);

            Integer hours = userHours.get(userId);
            double workloadPercentage = (double) hours / (double) totalHours * 100;

            statistics[statisticsCounter][0] = user.getName();
            statistics[statisticsCounter][1] = String.valueOf(hours);
            statistics[statisticsCounter][2] = Math.round(workloadPercentage)  + "%";
            statisticsCounter++;
        }
        statistics[statisticsCounter][0] = "Total";
        statistics[statisticsCounter][1] = String.valueOf(totalHours);
        statistics[statisticsCounter][2] = "100%";

        return statistics;
    }

}
