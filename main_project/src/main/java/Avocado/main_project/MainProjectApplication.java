package Avocado.main_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MainProjectApplication {
	public static void main(String[] args) {
		

        // Create a PasswordEncoder bean
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Hash a password
        String rawPassword = "yourpassword"; // Change the string by the password wanted
        String hashedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);
        
		SpringApplication.run(MainProjectApplication.class, args);
	}

}
