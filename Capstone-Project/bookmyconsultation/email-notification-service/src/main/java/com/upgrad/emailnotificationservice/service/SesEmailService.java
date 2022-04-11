package com.upgrad.emailnotificationservice.service;

import com.upgrad.emailnotificationservice.exception.EmailNotificationServiceException;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@Service
public class SesEmailService {
    private final FreeMarkerConfigurer configurer;
    private SesClient sesClient;
    @Value("${ses.service.access.key}")
    private String accessKey;
    @Value("${ses.service.secret.key}")
    private String secretKey;
    @Value("${smtp.aws.fromemail}")
    private String fromEmail;
    @Value("${smtp.aws.endpoint}")
    private String smtpEndPoint;
    @Value("${smtp.aws.username}")
    private String smtpUsername;
    @Value("${smtp.aws.password}")
    private String smtpPassword;

    @PostConstruct
    public void init() {
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKey, secretKey));
        sesClient = SesClient.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }

    public void verifyEmail(String emailId) {
        sesClient.verifyEmailIdentity(req -> req.emailAddress(emailId));
    }

    public void sendEmail(String emailId, String subject, String template) throws EmailNotificationServiceException {
        try {
            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("emailId", emailId);
            Template freeMarkerTemplate = configurer.getConfiguration().getTemplate(template);
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate, templateModel);
            sendSimpleMessage(emailId, subject, htmlBody);
        } catch (Exception e) {
            log.error("Exception while sending the email", e);
            throw new EmailNotificationServiceException("Exception while sending the email");
        }

    }

    private void sendSimpleMessage(String toEmail, String subject, String body) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = javax.mail.Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(fromEmail);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject(subject);
        msg.setContent(body, "text/html");
        Transport transport = session.getTransport();
        try {
            transport.connect(smtpEndPoint, smtpUsername, smtpPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception e) {
            log.error("Exception while sending the email to {}, Subject:{}", toEmail, subject, e);
        } finally {
            transport.close();
        }
    }


}
