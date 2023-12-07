package org.example.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setFrom("epetitiimd@gmail.com");
            helper.setTo(email);
            msg.setSubject("TradeIt Email Confirmation");
            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>Trade it Email Confirmation</title>\n" +
                    "</head>\n" +
                    "<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0;\">\n" +
                    "  <table role=\"presentation\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "    <tr>\n" +
                    "      <td align=\"center\" bgcolor=\"#f5f5f5\">\n" +
                    "        <table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                    "          <tr>\n" +
                    "            <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 40px 0;\">\n" +
                    "              <h1 style=\"color: #333333;\">Welcome to <img src=\"https://trade-it.vercel.app/assets/images/Logo.jpg\" alt=\"Logo\" style=\"vertical-align: middle; max-width: 150px; max-height: 150px; margin-bottom: 15px;\"></h1>\n" +
                    "              <p style=\"color: #666666; font-size: 16px; line-height: 1.6;\">Thank you for signing up. Please confirm your email address to activate your account.</p>\n" +
                    "              <a href=\"http://localhost:3000?emailToken=" + token + "\" style=\"display: inline-block; padding: 12px 24px; background-color: #0EB085; color: #ffffff; text-decoration: none; border-radius: 4px;\">Confirm Email</a>\n" +
                    "            </td>\n" +
                    "          </tr>\n" +
                    "        </table>\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </table>\n" +
                    "</body>\n" +
                    "</html>";
            helper.setText(htmlContent, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

}
