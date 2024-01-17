package Avocado.main_project.Entities.projectEvaluation;

import Avocado.main_project.Entities.activity.Activity;
import Avocado.main_project.Entities.criterion.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectEvaluationRepository extends JpaRepository<ProjectEvaluation, Long> {
    List<ProjectEvaluation> findByTeamId(Long teamId);

    List<ProjectEvaluation> findByCriterionId(Long criterionId);

    List<ProjectEvaluation> findByCourseId(Long courseId);

    List<ProjectEvaluation> findByStudentId(Long studentId);
}
