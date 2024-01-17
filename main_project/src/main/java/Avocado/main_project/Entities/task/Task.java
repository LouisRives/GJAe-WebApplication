package Avocado.main_project.Entities.task;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskId")
    private Long id;

    @Column(name = "ActivityID")
    private Long activityId;

    @Column(name = "TaskOrder")
    private Integer order;

    @Column(name = "TaskName")
    private String name;

    @Column(name = "TaskType")
    private String type;

    @Column(name = "Description")
    private String description;

    @Column(name = "StartDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "EndDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
    public Task() {
    }

    public Task(Long id,
                Long activityId,
                Integer order,
                String name,
                String type,
                String description,
                LocalDate startDate,
                LocalDate endDate) {
        this.id = id;
        this.activityId = activityId;
        this.order = order;
        this.name = name;
        this.type = type;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Task(Long activityId,
                Integer order,
                String name,
                String type,
                String description,
                LocalDate startDate,
                LocalDate endDate) {
        this.activityId = activityId;
        this.order = order;
        this.name = name;
        this.type = type;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", order=" + order +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
