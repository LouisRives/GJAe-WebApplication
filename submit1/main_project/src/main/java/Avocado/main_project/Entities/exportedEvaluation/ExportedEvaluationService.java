package Avocado.main_project.Entities.exportedEvaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportedEvaluationService {

    private final ExportedEvaluationRepository exportedEvaluationRepository;

    @Autowired
    public ExportedEvaluationService(ExportedEvaluationRepository exportedEvaluationRepository){
        this.exportedEvaluationRepository = exportedEvaluationRepository;
    }

}
