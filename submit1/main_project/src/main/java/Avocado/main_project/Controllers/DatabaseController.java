package Avocado.main_project.Controllers;

import Avocado.main_project.Entities.Student.Student;
import Avocado.main_project.Entities.Student.StudentService;
import Avocado.main_project.Entities.activity.Activity;
import Avocado.main_project.Entities.activity.ActivityService;
import Avocado.main_project.Entities.course.Course;
import Avocado.main_project.Entities.course.CourseService;
import Avocado.main_project.Entities.criterion.Criterion;
import Avocado.main_project.Entities.criterion.CriterionService;
import Avocado.main_project.Entities.projectEvaluation.ProjectEvaluation;
import Avocado.main_project.Entities.projectEvaluation.ProjectEvaluationService;
import Avocado.main_project.Entities.solver.Solver;
import Avocado.main_project.Entities.solver.SolverKey;
import Avocado.main_project.Entities.solver.SolverService;
import Avocado.main_project.Entities.task.Task;
import Avocado.main_project.Entities.task.TaskService;
import Avocado.main_project.Entities.team.Team;
import Avocado.main_project.Entities.team.TeamService;
import Avocado.main_project.Entities.user.Users;
import Avocado.main_project.Entities.user.UserService;
import Avocado.main_project.Entities.workload.Workload;
import Avocado.main_project.Entities.workload.WorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path="database")
public class DatabaseController {

    private final CourseService courseService;
    private final ActivityService activityService;
    private final TaskService taskService;
    private final UserService userService;
    private final SolverService solverService;
    private final WorkloadService workloadService;
    private final TeamService teamService;

    private final StudentService studentService;
    private final CriterionService criterionService;
    private final ProjectEvaluationService projectEvaluationService;

    @Autowired
    public DatabaseController(CourseService courseService,
                              ActivityService activityService,
                              TaskService taskService,
                              UserService userService,
                              SolverService solverService,
                              WorkloadService workloadService,
                              TeamService teamService,
                              StudentService studentService, CriterionService criterionService,
                              ProjectEvaluationService projectEvaluationService) {

        this.courseService = courseService;
        this.activityService = activityService;
        this.taskService = taskService;
        this.userService = userService;
        this.solverService = solverService;
        this.workloadService = workloadService;
        this.teamService = teamService;
        this.studentService = studentService;
        this.criterionService = criterionService;
        this.projectEvaluationService = projectEvaluationService;
    }

    @GetMapping("/populate")
    public ResponseEntity<String> PopulateDatabase() {
        Course course = courseService.createCourse("JAVA Course");

        Activity activity = activityService.createActivity("Lecture activity", course.getId());

        Task task = taskService.createTask(activity.getId(), 0, "Task 1", "Task 1 type",
                "This is the task description", LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 31));
        taskService.createTask(activity.getId(), 1, "Task 2", "Task 2 type",
                "This is another task description", LocalDate.of(2023, 10, 1),
                LocalDate.of(2024, 1, 31));

        Users user = userService.createUser("User111", "Teacher",
                "Mister Teacher", "teacher@teacher.com", "1234");
        userService.createUser("User222", "Admin", "Doctor Admin", "admin@admin.com",
                "12345");

        Long userId = user.getId();
        Long taskId = task.getId();
        SolverKey solverKey = new SolverKey(userId, taskId);
        solverService.createSolver(solverKey, true);

        workloadService.createWorkload(taskId, userId, "I have done my work", 10, 12);

        Team team1 = teamService.createTeam("Team no1", course.getId());
        Team team2 = teamService.createTeam("Team no2", course.getId());

        Set<Team> studentTeam1 = new HashSet<Team>();
        Set<Team> studentTeam2 = new HashSet<Team>();

        studentTeam1.add(team1);
        studentTeam2.add(team2);
        Student student = studentService.createStudent("Gui","gg.franco@campus.fct.unl.pt", studentTeam1);
        studentService.createStudent("Kuba","kbc@gmail.com", studentTeam1);
        Student student2 = studentService.createStudent("Louis","louis@gmail.com", studentTeam2);

        Criterion criterion = criterionService.createCriterion(course.getId(), "Category 1", "Criterion 1",
                "Check by doing everything", true, 50);

        projectEvaluationService.createProjectEvaluation(team1.getId(), course.getId(), student.getId(),criterion.getId(), 35,
                "Coulda been better bro");

        projectEvaluationService.createProjectEvaluation(team2.getId(), course.getId(), student2.getId(),criterion.getId(), 40,
                "pretty good");

        String htmlContent = "<div><p>Database populated!</p></div>";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<String> ReadDatabase() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("====== COURSES ======");
        List<Course> courses = courseService.getAllCourses();
        for (Course course : courses) {
            stringBuilder.append("<div><p>").append(course.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== ACTIVITIES ======");
        List<Activity> activities = activityService.getAllActivities();
        for (Activity activity : activities) {
            stringBuilder.append("<div><p>").append(activity.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== TASKS ======");
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            stringBuilder.append("<div><p>").append(task.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== USERS ======");
        List<Users> users = userService.getAllUsers();
        for (Users user : users) {
            stringBuilder.append("<div><p>").append(user.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== SOLVERS ======");
        List<Solver> solvers = solverService.getAllSolvers();
        for (Solver solver : solvers) {
            stringBuilder.append("<div><p>").append(solver.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== WORKLOADS ======");
        List<Workload> workloads = workloadService.getAllWorkloads();
        for (Workload workload : workloads) {
            stringBuilder.append("<div><p>").append(workload.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== TEAMS ======");
        List<Team> teams = teamService.getAllTeams();
        for (Team team : teams) {
            stringBuilder.append("<div><p>").append(team.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== CRITERIA ======");
        List<Criterion> criteria = criterionService.getAllCriteria();
        for (Criterion criterion : criteria) {
            stringBuilder.append("<div><p>").append(criteria.toString()).append("</p></div>");
        }

        stringBuilder.append("\n").append("====== PROJECT EVALUATION ======");
        List<ProjectEvaluation> projectEvaluations = projectEvaluationService.getAllProjectEvaluations();
        for (ProjectEvaluation projectEvaluation : projectEvaluations) {
            stringBuilder.append("<div><p>").append(projectEvaluation.toString()).append("</p></div>");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(stringBuilder.toString(), headers, HttpStatus.OK);
    }

    @GetMapping("/purge")
    public ResponseEntity<String> PurgeDatabase() {
        projectEvaluationService.deleteAllProjectEvaluations();
        criterionService.deleteAllCriteria();
        teamService.deleteAllTeams();
        workloadService.deleteAllWorkloads();
        solverService.deleteAllSolvers();
        taskService.deleteAllTasks();
        activityService.deleteAllActivities();
        courseService.deleteAllCourses();
        userService.deleteAllUsers();

        String htmlContent = "<div><p>Database purged!</p></div>";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }

}
