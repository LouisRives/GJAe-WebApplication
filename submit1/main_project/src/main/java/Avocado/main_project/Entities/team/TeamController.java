package Avocado.main_project.Entities.team;

import Avocado.main_project.Entities.Student.StudentService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;
    private final StudentService studentService;

    @Autowired
    public TeamController(TeamService teamService, StudentService studentService) {
        this.teamService = teamService;
        this.studentService = studentService;
    }

    @GetMapping("/test")
    public String testTeamController() {
        return "Team controller works!";
    }

    // Endpoint to create a new team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team.getTeamName(), team.getCourseId());
        return ResponseEntity.ok(createdTeam);
    }

    // Endpoint to get a team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        if (team != null) {
            return ResponseEntity.ok(team);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint to list all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    // Endpoint to update a team
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team.getTeamName(), team.getCourseId());
        if (updatedTeam != null) {
            return ResponseEntity.ok(updatedTeam);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint to delete a team
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        if (team != null) {
            teamService.deleteTeam(team);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    //@Transactional
    @PostMapping("/{teamId}/addStudent/{studentId}")
    public ResponseEntity<?> addStudentToTeam(@PathVariable Long teamId, @PathVariable Long studentId) {
        try {
            Team team = teamService.addStudentToTeam(teamId, studentId);
            return ResponseEntity.ok(team);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Unable to add student to team");
        }
    }
    @Transactional
    public ResponseEntity<?> removeStudentFromTeam(@PathVariable Long teamId, @PathVariable Long studentId) {
        Team team = teamService.removeStudentFromTeam(teamId, studentId);
        if (team != null) {
            return ResponseEntity.ok(team);
        }
        return ResponseEntity.badRequest().body("Unable to remove student from team");
    }
}
