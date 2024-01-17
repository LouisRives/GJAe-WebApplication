package Avocado.main_project.Entities.emailTemplate;

import javax.persistence.*;

@Entity
@Table(name = "emailtemplates")
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TemplateID")
    private Long id;

    @Column(name = "ActivityID")
    private Long activityId;

    @Column(name = "EmailSubject")
    private String emailSubject;

    @Column(name = "EmailBody")
    private String emailBody;

    public EmailTemplate() {
    }

    public EmailTemplate(Long id, Long activityId, String emailSubject, String emailBody) {
        this.id = id;
        this.activityId = activityId;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
    }

    public EmailTemplate(Long activityId, String emailSubject, String emailBody) {
        this.activityId = activityId;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @Override
    public String toString() {
        return "EmailTemplate{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailBody='" + emailBody + '\'' +
                '}';
    }
}
