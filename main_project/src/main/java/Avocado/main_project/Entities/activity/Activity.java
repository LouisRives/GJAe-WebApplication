package Avocado.main_project.Entities.activity;

import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ActivityID")
    private Long id;

    @Column(name = "ActivityName")
    private String name;

    @Column(name = "CourseID")
    private Long courseId;

    public Activity() {
    }

    public Activity(Long id, String name, Long courseId) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
    }

    public Activity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
