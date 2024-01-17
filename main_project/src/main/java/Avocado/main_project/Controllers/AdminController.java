package Avocado.main_project.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import Avocado.main_project.Login.CustomUserDetails;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("role", userDetails.getRole());
        model.addAttribute("email", userDetails.getEmail());
        return "admin";
    }
}
