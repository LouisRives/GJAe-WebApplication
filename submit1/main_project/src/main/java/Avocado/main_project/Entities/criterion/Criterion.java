package Avocado.main_project.Entities.criterion;

import javax.persistence.*;

@Entity
@Table(name = "criteria")
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CriterionID")
    private Long id;

    @Column(name = "CourseID")
    private Long courseId;


    @Column(name = "Category")
    private String category;

    @Column(name = "CriterionName")
    private String criterionName;

    @Column(name = "CheckProcedure")
    private String checkProcedure;

    @Column(name = "EvaluationMethod")
    private Boolean evaluationMethod;

    @Column(name = "MaxPoints")
    private Integer maxPoints;

    public Criterion() {
    }

    public Criterion(Long id,
                     Long courseId,
                     String category,
                     String criterionName,
                     String checkProcedure,
                     Boolean evaluationMethod,
                     Integer maxPoints) {
        this.id = id;
        this.courseId = courseId;
        this.category = category;
        this.criterionName = criterionName;
        this.checkProcedure = checkProcedure;
        this.evaluationMethod = evaluationMethod;
        this.maxPoints = maxPoints;
    }

    public Criterion(Long courseId,
                     String category,
                     String criterionName,
                     String checkProcedure,
                     Boolean evaluationMethod,
                     Integer maxPoints) {

        this.courseId = courseId;
        this.category = category;
        this.criterionName = criterionName;
        this.checkProcedure = checkProcedure;
        this.evaluationMethod = evaluationMethod;
        this.maxPoints = maxPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    public String getCheckProcedure() {
        return checkProcedure;
    }

    public void setCheckProcedure(String checkProcedure) {
        this.checkProcedure = checkProcedure;
    }

    public Boolean getEvaluationMethod() {
        return evaluationMethod;
    }

    public void setEvaluationMethod(Boolean evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public String toString() {
        return "criterion{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", category='" + category + '\'' +
                ", criterionName='" + criterionName + '\'' +
                ", checkProcedure='" + checkProcedure + '\'' +
                ", evaluationMethod='" + evaluationMethod + '\'' +
                ", maxPoints=" + maxPoints +
                '}';
    }
}