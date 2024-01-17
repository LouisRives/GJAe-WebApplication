package Avocado.main_project.Entities.projectEvaluation;

import javax.persistence.*;

@Entity
@Table(name = "projectevaluations")
public class ProjectEvaluation {

    //missing student

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EvaluationID")
    private Long id;

    @Column(name = "TeamID")
    private Long teamId;

    @Column(name = "courseId")
    private Long courseId;

    @Column(name = "studentId")
    private Long studentId;

    @Column(name = "CriterionID")
    private Long criterionId;

    @Column(name = "Points")
    private Integer points;

    @Column(name = "Feedback")
    private String feedback;

    public ProjectEvaluation() {
    }

    public ProjectEvaluation(Long id,Long courseId,Long studentId, Long teamId, Long criterionId, Integer points, String feedback) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.teamId = teamId;
        this.criterionId = criterionId;
        this.points = points;
        this.feedback = feedback;
    }

    public ProjectEvaluation(Long teamId,Long courseId,Long studentId, Long criterionId, Integer points, String feedback) {
        this.teamId = teamId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.criterionId = criterionId;
        this.points = points;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(Long criterionId) {
        this.criterionId = criterionId;
    }

    public Integer getPoints() {
        return points;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "ProjectEvaluation{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", teamId=" + teamId +
                ", studentId=" + studentId +
                ", criterionId=" + criterionId +
                ", points=" + points +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
