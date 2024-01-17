package Avocado.main_project.Entities.workload;

import javax.persistence.*;

@Entity
@Table(name = "workloads")
public class Workload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WorkloadID")
    private Long id;

    @Column(name = "TaskID")
    private Long taskId;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "Report")
    private String report;

    @Column(name = "HoursSpent")
    private Integer hoursSpent;

    @Column(name = "StudentsDevoted")
    private Integer studentsDevoted;

    public Workload() {
    }

    public Workload(Long id, Long taskId, Long userId, String report, Integer hoursSpent, Integer studentsDevoted) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.report = report;
        this.hoursSpent = hoursSpent;
        this.studentsDevoted = studentsDevoted;
    }

    public Workload(Long taskId, Long userId, String report, Integer hoursSpent, Integer studentsDevoted) {
        this.taskId = taskId;
        this.userId = userId;
        this.report = report;
        this.hoursSpent = hoursSpent;
        this.studentsDevoted = studentsDevoted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(Integer hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public Integer getStudentsDevoted() {
        return studentsDevoted;
    }

    public void setStudentsDevoted(Integer studentsDevoted) {
        this.studentsDevoted = studentsDevoted;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "Workload{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", userId=" + userId +
                ", report='" + report + '\'' +
                ", hoursSpent=" + hoursSpent +
                ", studentsDevoted=" + studentsDevoted +
                '}';
    }
}
