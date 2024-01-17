package Avocado.main_project.Entities.team;

import Avocado.main_project.Entities.Student.Student;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TeamID")
    private Long id;

    @Column(name = "TeamName")
    private String teamName;

    @Column(name = "CourseID")
    private Long courseId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "TeamStudents",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();
    public Team() {
    }

    public Team(Long id, String teamName, Long courseId, Set<Student> students) {
        this.id = id;
        this.teamName = teamName;
        this.courseId = courseId;
        this.students = students;
    }

    public Team(Long id, String teamName, Long courseId) {
        this.id = id;
        this.teamName = teamName;
        this.courseId = courseId;
    }

    public Team(String teamName, Long courseId) {
        this.teamName = teamName;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }



    @Override
    public String toString() {
        return "team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", courseId=" + courseId +
                '}';
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
