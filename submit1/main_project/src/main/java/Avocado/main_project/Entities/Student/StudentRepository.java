package Avocado.main_project.Entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
