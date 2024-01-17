package Avocado.main_project.Entities.team;

import Avocado.main_project.Entities.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> getTeamsByCourseId(Long courseId);
}
