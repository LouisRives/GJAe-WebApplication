package Avocado.main_project.Entities.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByUsername(String username);
}
