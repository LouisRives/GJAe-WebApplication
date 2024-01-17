package Avocado.main_project.Entities.team;

import Avocado.main_project.Entities.Student.Student;
import Avocado.main_project.Entities.Student.StudentRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, StudentRepository studentRepository) {
        this.teamRepository = teamRepository;
        this.studentRepository = studentRepository;
    }

    public Team createTeam(String teamName, Long courseId) {
        Team team = new Team();
        team.setTeamName(teamName);
        team.setCourseId(courseId);
        return teamRepository.save(team);
    }

    public Team getTeamById(long id){
        return teamRepository.findById(id).orElse(null);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    public List<Team> getAllTeamsByCourseId(Long courseId) {
        return teamRepository.getTeamsByCourseId(courseId);
    }

    @Transactional
    public Set<Student> getStudentsByTeamId(Long teamId){
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team != null) {
            // Initialize the students collection
            team.getStudents().size();
            return team.getStudents();
        }
        return new HashSet<>();
    }


    public Team updateTeam(Long id, String teamName, Long courseId) {
        Team team = getTeamById(id);
        if (team != null) {
            team.setTeamName(teamName);
            team.setCourseId(courseId);

            teamRepository.save(team);
            return team;
        }
        return null;
    }

    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }

    public void deleteAllTeams() {
        teamRepository.deleteAll();
    }

    @Transactional
    public Team addStudentToTeam(Long teamId, Long studentId) {
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (teamOpt.isPresent() && studentOpt.isPresent()) {
            Team team = teamOpt.get();
            team.getStudents().add(studentOpt.get());
            return teamRepository.save(team);
        } else {
            throw new EntityNotFoundException("Team or Student not found");
        }
    }

    public Team removeStudentFromTeam(Long teamId, Long studentId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (team != null && student != null && team.getStudents().contains(student)) {
            team.getStudents().remove(student);
            return teamRepository.save(team);
        }
        return null;
    }
}
