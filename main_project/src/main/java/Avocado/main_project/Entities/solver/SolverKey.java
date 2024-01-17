package Avocado.main_project.Entities.solver;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SolverKey implements Serializable {
    private Long userId;
    private Long taskId;

    public SolverKey() {
    }

    public SolverKey(Long userId, Long taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "SolverKey{" +
                "userId=" + userId +
                ", taskId=" + taskId +
                '}';
    }
}
