package nn.dgordeev.stickytwits.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String email, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);

        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
