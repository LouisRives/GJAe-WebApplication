package Avocado.main_project.Entities.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SolverService {

    private final SolverRepository solverRepository;

    @Autowired
    public SolverService(SolverRepository solverRepository){
        this.solverRepository = solverRepository;
    }

    public Solver createSolver(SolverKey id, Boolean mainSolver) {
        Solver solver = new Solver();

        solver.setId(id);
        solver.setMainSolver(mainSolver);

        return solverRepository.save(solver);
    }

    public Solver createSolver(Solver solver) {
        return solverRepository.save(solver);
    }

    public Solver getSolverBySolverKey(SolverKey id){
        return solverRepository.findById(id).orElse(null);
    }

    public List<Solver> getAllSolvers() {
        return solverRepository.findAll();
    }

    public Solver updateSolver(SolverKey id, Boolean mainSolver) {
        Solver solver = getSolverBySolverKey(id);
        if (solver != null) {
            solver.setMainSolver(mainSolver);

            solverRepository.save(solver);
            return solver;
        }
        return null;
    }

    public void deleteSolver(Solver solver) {
        solverRepository.delete(solver);
    }

    public void deleteAllSolvers() {
        solverRepository.deleteAll();
    }

    public List<Solver> getSolversByTaskId(Long taskId) {
        List<Solver> solvers = getAllSolvers();
        for (int i = solvers.size() - 1; i >= 0; i--) {
            Solver solver = solvers.get(i);
            SolverKey key = solver.getId();
            if (!Objects.equals(key.getTaskId(), taskId)) {
                solvers.remove(i);
            }
        }
        return solvers;
    }
    
    @Transactional
    public void deleteSolversByTaskId(Long taskId) {
        solverRepository.deleteByTaskId(taskId);
    }
}