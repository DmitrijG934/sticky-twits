package nn.dgordeev.stickytwits.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private String debug;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setHost(host);
        Properties javaMailProperties = javaMailSender.getJavaMailProperties();

        javaMailProperties.put("mail.debug", debug);
        javaMailProperties.put("mail.transport.protocol", protocol);
        javaMailProperties.setProperty("mail.smtp.auth", auth);
        javaMailProperties.setProperty("mail.smtp.starttls.enable", enable);
        javaMailProperties.put("mail.smtp.ssl.trust", host);

        return javaMailSender;
    }
}
