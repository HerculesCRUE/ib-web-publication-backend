package es.um.asio.service.service.mail.impl;

import es.um.asio.service.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(List<String> tos, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        for (String to : tos) {
            msg.setTo(to);
        }

        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
