package Avocado.main_project.Entities.workload;

import Avocado.main_project.Entities.activity.Activity;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkloadService {

    private final WorkloadRepository workloadRepository;

    @Autowired
    public WorkloadService(WorkloadRepository workloadRepository){
        this.workloadRepository = workloadRepository;
    }

    public Workload createWorkload(Long taskId, Long userId, String report, Integer hoursSpent, Integer studentsDevoted) {
        Workload workload = new Workload();
        workload.setTaskId(taskId);
        workload.setUserId(userId);
        workload.setReport(report);
        workload.setHoursSpent(hoursSpent);
        workload.setStudentsDevoted(studentsDevoted);

        return workloadRepository.save(workload);
    }

    public Workload createWorkload(Workload workload) {
        return workloadRepository.save(workload);
    }

    public Workload getWorkloadById(long id){
        return workloadRepository.findById(id).orElse(null);
    }

    public List<Workload> getAllWorkloads() {
        return workloadRepository.findAll();
    }

    public Workload updateWorkload(Long id, Long taskId, Long userId, String report, Integer hoursSpent, Integer studentsDevoted) {
        Workload workload = getWorkloadById(id);
        if (workload != null) {
            workload.setTaskId(taskId);
            workload.setUserId(userId);
            workload.setReport(report);
            workload.setHoursSpent(hoursSpent);
            workload.setStudentsDevoted(studentsDevoted);

            workloadRepository.save(workload);
            return workload;
        }
        return null;
    }

    public void deleteWorkload(Workload workload) {
        workloadRepository.delete(workload);
    }

    public void deleteAllWorkloads() {
        workloadRepository.deleteAll();
    }

    public List<Workload> getWorkloadsByTaskId(long taskId){return workloadRepository.findByTaskId(taskId);}

}
