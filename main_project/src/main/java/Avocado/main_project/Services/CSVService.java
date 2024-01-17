package Avocado.main_project.Services;

import Avocado.main_project.Entities.Student.Student;
import Avocado.main_project.Entities.Student.StudentRepository;
import Avocado.main_project.Entities.criterion.Criterion;
import Avocado.main_project.Entities.criterion.CriterionRepository;
import Avocado.main_project.Entities.projectEvaluation.ProjectEvaluation;
import Avocado.main_project.Entities.projectEvaluation.ProjectEvaluationRepository;
import Avocado.main_project.Entities.team.Team;
import Avocado.main_project.Entities.team.TeamCSV;
import Avocado.main_project.Entities.team.TeamRepository;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CSVService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectEvaluationRepository projectEvaluationRepository;
    @Autowired
    private CriterionRepository criterionRepository;
    public void importStudents(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        try (Reader reader = new BufferedReader(new FileReader(file))) {
            ColumnPositionMappingStrategy<Student> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Student.class);
            strategy.setColumnMapping(new String[]{"id", "name", "email"});

            CsvToBean<Student> csvToBean = new CsvToBeanBuilder<Student>(reader)
                    .withMappingStrategy(strategy)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)
                    .build();

            List<Student> studentsFromCSV = csvToBean.parse();

            for (Student studentFromCSV : studentsFromCSV) {
                // Check if a student with the same ID already exists in the database
                Optional<Student> existingStudent = studentRepository.findById(studentFromCSV.getId());

                if (existingStudent.isPresent()) {
                    // Update existing student with new data
                    Student updatedStudent = existingStudent.get();
                    updatedStudent.setName(studentFromCSV.getName());
                    updatedStudent.setEmail(studentFromCSV.getEmail());
                    studentRepository.save(updatedStudent);
                } else {
                    // If the student doesn't exist, save it as a new entry
                    studentRepository.save(studentFromCSV);
                }
            }
        } finally {
            // Delete the file once processing is complete
            if (file.exists()) {
                boolean isDeleted = file.delete();
                if (!isDeleted) {
                    // Log or handle the situation if the file couldn't be deleted
                    System.err.println("Failed to delete the file: " + filePath);
                }
            }
        }
    }


    public void importTeams(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        try (Reader reader = new BufferedReader(new FileReader(file))) {
            ColumnPositionMappingStrategy<TeamCSV> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(TeamCSV.class);
            strategy.setColumnMapping(new String[]{"teamId", "teamName", "courseId", "studentIds"});

            CsvToBean<TeamCSV> csvToBean = new CsvToBeanBuilder<TeamCSV>(reader)
                    .withMappingStrategy(strategy)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)
                    .build();

            List<TeamCSV> teamsFromCSV = csvToBean.parse();

            // Print parsed data for debugging TODO delete after testing
            for (TeamCSV team : teamsFromCSV) {
                System.out.println("Parsed Team: " + team);
            }


            for (TeamCSV teamFromCSV : teamsFromCSV) {
                Team team;
                Optional<Team> existingTeam = teamRepository.findById(teamFromCSV.getTeamId());

                if (existingTeam.isPresent()) {
                    team = existingTeam.get();
                } else {
                    team = new Team();
                    team.setId(teamFromCSV.getTeamId());
                }

                team.setTeamName(teamFromCSV.getTeamName());
                team.setCourseId(teamFromCSV.getCourseId());

                Set<Student> students = new
                        HashSet<>();
                if (teamFromCSV.getStudentIds() != null && !teamFromCSV.getStudentIds().isEmpty()) {
                    String[] ids = teamFromCSV.getStudentIds().split(",");
                    for (String idStr : ids) {
                        Long studentId = Long.parseLong(idStr.trim());
                        studentRepository.findById(studentId).ifPresent(students::add);
                    }
                }

                team.setStudents(students);
                teamRepository.save(team);
            }
            }


        }
    public void exportEvaluations(Writer writer, Long courseId) throws IOException {
        List<ProjectEvaluation> evaluations = projectEvaluationRepository.findByCourseId(courseId);

        CSVWriter csvWriter = new CSVWriter(writer);

        // Write header
        String[] header = {"Criterion Name", "Student ID", "Feedback", "Points"};

        csvWriter.writeNext(header);


        for (ProjectEvaluation evaluation : evaluations) {
            // Check if both IDs are present
            if (evaluation.getCriterionId() != null && evaluation.getStudentId() != null) {
                Criterion criterion = criterionRepository.findById(evaluation.getCriterionId()).orElse(null);
                Student student = studentRepository.findById(evaluation.getStudentId()).orElse(null);

                // Continue only if both Criterion and Student are found
                if (criterion != null && student != null) {
                    String criterionName = criterion.getCriterionName();
                    String feedback = evaluation.getFeedback();
                    Integer points = evaluation.getPoints();

                    String[] data = {
                            criterionName,
                            student.getId().toString(),
                            feedback,
                            points != null ? points.toString() : ""
                    };
                    csvWriter.writeNext(data);
                }
            }
        }
        csvWriter.close();
    }

}

