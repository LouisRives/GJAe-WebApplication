package Avocado.main_project.Entities.emailTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="email-templates")
public class EmailTemplateController {

    private final EmailTemplateService emailTemplateService;

    @Autowired
    public EmailTemplateController(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    @GetMapping("/test")
    public String testEmailTemplateController() {
        return "Email template controller works!";
    }

}
