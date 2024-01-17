package Avocado.main_project.Entities.exportedEvaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportedEvaluationRepository extends JpaRepository<ExportedEvaluation, Long> {
}
