package Avocado.main_project.Entities.course;

import Avocado.main_project.Entities.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ActivityService activityService;

    @Autowired
    public CourseService(CourseRepository courseRepository, ActivityService activityService){
        this.courseRepository = courseRepository;
        this.activityService = activityService;
    }

    public Course createCourse(String name) {
        Course course = new Course();
        course.setName(name);

        return courseRepository.save(course);
    }

    public Course getCourseById(long id){
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Long courseId, String name) {
        Course course = getCourseById(courseId);
        if (course != null) {
            course.setName(name);
            courseRepository.save(course);
            return course;
        }
        return null;
    }

    public void deleteCourse(Course course) {
        activityService.deleteAllActivitiesByCourseId(course.getId());
        courseRepository.delete(course);
    }

    public void deleteAllCourses() {
        activityService.deleteAllActivities();
        courseRepository.deleteAll();
    }

}
