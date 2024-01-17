package Avocado.main_project.Entities.Student;

import Avocado.main_project.Entities.team.Team;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "students")
    @JsonBackReference
    private Set<Team> teams;

    public Student() {

    }

    public Student(Long id, String name, String email, Set<Team> teams) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.teams = teams;
    }

    public Student(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
