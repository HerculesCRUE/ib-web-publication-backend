package es.um.asio.service.service.mail;

import java.util.List;

public interface EmailService {
    public void sendSimpleMail(List<String> to, String subject, String text);
}
