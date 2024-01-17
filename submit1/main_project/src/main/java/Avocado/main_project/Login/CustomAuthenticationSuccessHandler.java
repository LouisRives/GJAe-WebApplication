package Avocado.main_project.Login;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        // Get the authorities of the authenticated user
        // You can customize this based on your UserDetails implementation
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        // Determine the target URL based on the user's role
        if ("USER".equals(role)) {
            return "/resources/templates/teacher.html";
        } else if ("ADMIN".equals(role)) {
            return "/resources/templates/admin.html";
        } else {
            // Handle other roles or scenarios
            return "/default";
        }
    }
}
