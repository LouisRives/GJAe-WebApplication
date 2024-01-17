package Avocado.main_project.Entities.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Users createUser(String username, String role, String name, String email, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setRole(role.toUpperCase(Locale.ROOT));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);
    }

    public Users getUserById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users updateUser(Long userId, String name, String email, String password) {
        Users user = getUserById(userId);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public void deleteUser(Users user) {
        userRepository.delete(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
