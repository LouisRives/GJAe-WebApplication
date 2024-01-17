package Avocado.main_project.Entities.user;

import Avocado.main_project.Services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private CSVService csvService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }




    @PostMapping("/add")
    public Users addNewUser(@RequestBody Users user) {
        return userService.createUser(user.getUsername(), user.getRole(), user.getName(), user.getEmail(), user.getPassword());
    }

    /*@GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }*/

    @GetMapping("/allusers")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
