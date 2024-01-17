package Avocado.main_project.Entities.solver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SolverRepository extends JpaRepository<Solver, SolverKey> {
	@Modifying
    @Query("DELETE FROM Solver s WHERE s.id.taskId = :taskId")
    void deleteByTaskId(@Param("taskId") Long taskId);
}
