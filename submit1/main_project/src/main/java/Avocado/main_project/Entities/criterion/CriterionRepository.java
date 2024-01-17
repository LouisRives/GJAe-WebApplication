package Avocado.main_project.Entities.criterion;

import Avocado.main_project.Entities.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Long> {
    List<Criterion> findByCourseId(Long courseId);
}
