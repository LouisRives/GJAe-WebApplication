package Avocado.main_project.Entities.exportedEvaluation;

import javax.persistence.*;

@Entity
@Table(name = "exportedevaluations")
public class ExportedEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EvaluationID")
    private Long id;

    public ExportedEvaluation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExportedEvaluation{" +
                "id=" + id +
                '}';
    }
}
