package Avocado.main_project.Entities.solver;

import javax.persistence.*;

@Entity
@Table(name = "solvers")
public class Solver {

    @EmbeddedId
    private SolverKey id;

    @Column(name = "MainSolver")
    private Boolean mainSolver;

    public Solver() {
    }

    public Solver(SolverKey id, Boolean mainSolver) {
        this.id = id;
        this.mainSolver = mainSolver;
    }

    public SolverKey getId() {
        return id;
    }

    public void setId(SolverKey id) {
        this.id = id;
    }

    public Boolean getMainSolver() {
        return mainSolver;
    }

    public void setMainSolver(Boolean mainSolver) {
        this.mainSolver = mainSolver;
    }

    @Override
    public String toString() {
        return "Solver{" +
                "id=" + id.toString() +
                ", mainSolver=" + mainSolver +
                '}';
    }
}
