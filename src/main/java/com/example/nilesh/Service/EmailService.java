package com.example.nilesh.service;

import com.example.nilesh.entity.ContactMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.admin.email}")
    private String adminEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactEmails(ContactMessage message) {
        sendAdminEmail(message);
        sendUserEmail(message);
    }

    private void sendAdminEmail(ContactMessage message) {
        String subject = "New Portfolio Contact Message";

        String html = """
                <div style="font-family:Arial,sans-serif;background:#f4f7fb;padding:30px;">
                    <div style="max-width:650px;margin:auto;background:white;border-radius:14px;padding:30px;box-shadow:0 10px 30px rgba(0,0,0,0.08);">
                        <h2 style="color:#2563eb;margin-bottom:10px;">New Contact Message</h2>
                        <p style="color:#555;">Someone submitted your portfolio contact form.</p>

                        <hr style="border:none;border-top:1px solid #eee;margin:25px 0;"/>

                        <p><strong>Name:</strong> %s</p>
                        <p><strong>Email:</strong> %s</p>

                        <div style="margin-top:20px;padding:18px;background:#f8fafc;border-left:4px solid #2563eb;border-radius:8px;">
                            <p style="margin:0;line-height:1.7;color:#333;">%s</p>
                        </div>

                        <p style="margin-top:30px;color:#777;font-size:13px;">
                            This message was sent from your portfolio website.
                        </p>
                    </div>
                </div>
                """.formatted(
                message.getName(),
                message.getEmail(),
                message.getMessage()
        );

        sendHtmlMail(adminEmail, subject, html);
    }

    private void sendUserEmail(ContactMessage message) {
        String subject = "Thanks for contacting Nilesh Kumar";

        String html = """
                <div style="font-family:Arial,sans-serif;background:#f4f7fb;padding:30px;">
                    <div style="max-width:650px;margin:auto;background:white;border-radius:14px;padding:30px;box-shadow:0 10px 30px rgba(0,0,0,0.08);">
                        <h2 style="color:#2563eb;margin-bottom:10px;">Thank you, %s!</h2>

                        <p style="color:#333;line-height:1.7;">
                            I have received your message and will get back to you soon.
                        </p>

                        <div style="margin-top:20px;padding:18px;background:#f8fafc;border-left:4px solid #22c55e;border-radius:8px;">
                            <p style="margin:0;color:#555;"><strong>Your message:</strong></p>
                            <p style="margin-top:10px;line-height:1.7;color:#333;">%s</p>
                        </div>

                        <p style="margin-top:30px;color:#555;">
                            Regards,<br/>
                            <strong>Nilesh Kumar</strong><br/>
                            Java Full Stack Developer
                        </p>
                    </div>
                </div>
                """.formatted(
                message.getName(),
                message.getMessage()
        );

        sendHtmlMail(message.getEmail(), subject, html);
    }

    private void sendHtmlMail(String to, String subject, String html) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Email sending failed: " + e.getMessage());
        }
    }
}
