package Avocado.main_project.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Avocado.main_project.Entities.user.UserRepository;
import Avocado.main_project.Entities.user.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Implementation of the loadUserByUsername method
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Retrieve user information from the UserRepository based on the provided username
        Users user = userRepository.findByUsername(username);

        // Check if the user exists
        if(user == null) {
            // Throw an exception if the user is not found
            throw new UsernameNotFoundException("User Not Found");
        }

        // Create and return a CustomUserDetails object based on the retrieved user information
        return new CustomUserDetails(user);
    }
}
