package Avocado.main_project.Entities.projectEvaluation;

import Avocado.main_project.Entities.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectEvaluationService {

    private final ProjectEvaluationRepository projectEvaluationRepository;

    @Autowired
    public ProjectEvaluationService(ProjectEvaluationRepository projectEvaluationRepository){
        this.projectEvaluationRepository = projectEvaluationRepository;
    }

    public ProjectEvaluation createProjectEvaluation(Long teamId,Long courseId,Long studentId, Long criterionId, Integer points, String feedback) {
        ProjectEvaluation projectEvaluation = new ProjectEvaluation();
        projectEvaluation.setTeamId(teamId);
        projectEvaluation.setStudentId(studentId);
        projectEvaluation.setCourseId(courseId);
        projectEvaluation.setCriterionId(criterionId);
        projectEvaluation.setPoints(points);
        projectEvaluation.setFeedback(feedback);

        return projectEvaluationRepository.save(projectEvaluation);
    }

    public ProjectEvaluation getProjectEvaluationById(long id){
        return projectEvaluationRepository.findById(id).orElse(null);
    }

    public List<ProjectEvaluation> getAllProjectEvaluations() {
        return projectEvaluationRepository.findAll();
    }

    public List<ProjectEvaluation> getProjectEvaluationsByCourseId(Long courseId) {
        return projectEvaluationRepository.findByCourseId(courseId);
    }

    public ProjectEvaluation updateProjectEvaluation(Long id, Long teamId,Long studentId, Long criterionId, Integer points, String feedback) {
        ProjectEvaluation projectEvaluation = getProjectEvaluationById(id);
        if (projectEvaluation != null) {
            projectEvaluation.setTeamId(teamId);
            projectEvaluation.setStudentId(studentId);
            projectEvaluation.setCriterionId(criterionId);
            projectEvaluation.setPoints(points);
            projectEvaluation.setFeedback(feedback);

            projectEvaluationRepository.save(projectEvaluation);
            return projectEvaluation;
        }
        return null;
    }

    public void deleteProjectEvaluation(ProjectEvaluation projectEvaluation) {
        projectEvaluationRepository.delete(projectEvaluation);
    }

    public void deleteAllProjectEvaluations() {
        projectEvaluationRepository.deleteAll();
    }

    public List<ProjectEvaluation> getProjectEvaluationsByTeamId(long teamId){
        return projectEvaluationRepository.findByTeamId(teamId);
    }

    public List<ProjectEvaluation> getProjectEvaluationsByStudentId(long studentId){
        return projectEvaluationRepository.findByStudentId(studentId);
    }

    public List<ProjectEvaluation> getProjectEvaluationsByCriterionId(long criterionId){
        return projectEvaluationRepository.findByCriterionId(criterionId);
    }

}
