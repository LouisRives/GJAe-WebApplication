package Avocado.main_project.Entities.workload;

import Avocado.main_project.Entities.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkloadRepository extends JpaRepository<Workload, Long> {
    List<Workload> findByTaskId(Long taskId);
}
