package org.example.email;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private EmailDetails emailDetails;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendVerificationToken(String email, String token) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("epetitiimd@gmail.com");
            msg.setTo(email);
            msg.setSubject("Verification Token");
            msg.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/api/auth/registration/confirm?token=" + token);
            javaMailSender.send(msg);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
