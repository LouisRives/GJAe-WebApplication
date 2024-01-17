package Avocado.main_project.Entities.activity;

import Avocado.main_project.Entities.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    private final TaskService taskService;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, TaskService taskService) {
        this.activityRepository = activityRepository;
        this.taskService = taskService;
    }

    public Activity createActivity(String name, Long courseId) {
        Activity activity = new Activity();
        activity.setName(name);
        activity.setCourseId(courseId);

        return activityRepository.save(activity);
    }

    public Activity getActivityById(long id){
        return activityRepository.findById(id).orElse(null);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByCourse(long id){return activityRepository.findByCourseId(id);}

    public Activity updateActivity(Long activityId, String name, Long courseId) {
        Activity activity = getActivityById(activityId);
        if (activity != null) {
            activity.setName(name);
            activity.setCourseId(courseId);
            activityRepository.save(activity);
            return activity;
        }
        return null;
    }

    public void deleteActivity(Activity activity) {
        taskService.deleteTasksByActivityId(activity.getId());
        activityRepository.delete(activity);
    }

    public void deleteAllActivities() {
        activityRepository.deleteAll();
    }

    public void deleteAllActivitiesByCourseId(Long courseId) {
        List<Activity> activities = getActivitiesByCourse(courseId);
        for (Activity activity: activities){
            deleteActivity(activity);
        }
    }

}
