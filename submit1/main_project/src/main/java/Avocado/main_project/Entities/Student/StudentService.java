package Avocado.main_project.Entities.Student;

import Avocado.main_project.Entities.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student createStudent(Long id, String name, String email) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        return studentRepository.save(student);
    }
    public Student createStudent(String name, String email, Set<Team> teams) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setTeams(teams);
        return studentRepository.save(student);
    }



    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
    }
}
