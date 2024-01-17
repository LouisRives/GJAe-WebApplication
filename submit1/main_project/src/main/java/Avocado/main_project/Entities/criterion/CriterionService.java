package Avocado.main_project.Entities.criterion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriterionService {

    private final CriterionRepository criterionRepository;

    @Autowired
    public CriterionService(CriterionRepository criterionRepository){
        this.criterionRepository = criterionRepository;
    }

    public Criterion createCriterion(Long courseId,
                                     String category,
                                     String criterionName,
                                     String checkProcedure,
                                     Boolean evaluationMethod,
                                     Integer maxPoints)
    {
        Criterion criterion = new Criterion();
        criterion.setCourseId(courseId);
        criterion.setCategory(category.toUpperCase());
        criterion.setCriterionName(criterionName);
        criterion.setCheckProcedure(checkProcedure);
        criterion.setEvaluationMethod(evaluationMethod);
        criterion.setMaxPoints(maxPoints);

        return criterionRepository.save(criterion);
    }

    public Criterion createCriterion(Criterion criterion){
        criterion.setCategory(criterion.getCategory().toUpperCase());

        return criterionRepository.save(criterion);
    }

    public Criterion getCriterionById(long id){
        return criterionRepository.findById(id).orElse(null);
    }

    public List<Criterion> getAllCriteria() {
        return criterionRepository.findAll();
    }

    public Criterion updateCriterion(Long id,
                                     Long courseId,
                                     String category,
                                     String criterionName,
                                     String checkProcedure,
                                     Boolean evaluationMethod,
                                     Integer maxPoints) {
        Criterion criterion = getCriterionById(id);
        if (criterion != null) {
            criterion.setCourseId(courseId);
            criterion.setCategory(category.toUpperCase());
            criterion.setCriterionName(criterionName);
            criterion.setCheckProcedure(checkProcedure);
            criterion.setEvaluationMethod(evaluationMethod);
            criterion.setMaxPoints(maxPoints);

            criterionRepository.save(criterion);
            return criterion;
        }
        return null;
    }

    public void deleteCriterion(Criterion criterion) {
        criterionRepository.delete(criterion);
    }

    public void deleteAllCriteria() {
        criterionRepository.deleteAll();
    }

    public List<Criterion> getCriteriaByCourseId(long courseId){return criterionRepository.findByCourseId(courseId);}

}
