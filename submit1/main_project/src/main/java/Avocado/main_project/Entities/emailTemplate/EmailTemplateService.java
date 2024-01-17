package Avocado.main_project.Entities.emailTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public EmailTemplateService(EmailTemplateRepository emailTemplateRepository){
        this.emailTemplateRepository = emailTemplateRepository;
    }

    public EmailTemplate createEmailTemplate(Long activityId, String emailSubject, String emailBody) {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setActivityId(activityId);
        emailTemplate.setEmailSubject(emailSubject);
        emailTemplate.setEmailBody(emailBody);

        return emailTemplateRepository.save(emailTemplate);
    }

    public EmailTemplate getEmailTemplateById(long id){
        return emailTemplateRepository.findById(id).orElse(null);
    }

    public List<EmailTemplate> getAllEmailTemplates() {
        return emailTemplateRepository.findAll();
    }

    public EmailTemplate updateEmailTemplate(Long id, Long activityId, String emailSubject, String emailBody) {
        EmailTemplate emailTemplate = getEmailTemplateById(id);
        if (emailTemplate != null) {
            emailTemplate.setActivityId(activityId);
            emailTemplate.setEmailSubject(emailSubject);
            emailTemplate.setEmailBody(emailBody);

            emailTemplateRepository.save(emailTemplate);
            return emailTemplate;
        }
        return null;
    }

    public void deleteEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplateRepository.delete(emailTemplate);
    }

    public void deleteAllEmailTemplates() {
        emailTemplateRepository.deleteAll();
    }

}
