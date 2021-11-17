package es.um.asio.service.service.mail.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import es.um.asio.service.repository.SparqlQueryRepository;
import es.um.asio.service.service.mail.EmailService;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired(required = true)
	private SparqlQueryRepository repository;
	
	 @Value("${spring.mail.username}")
	private String from;

	@Override
	public void sendSimpleMail(List<String> tos, String subject, String text) {
		SimpleMailMessage msg = new SimpleMailMessage();
		for (String to : tos) {
			msg.setTo(to);
		}
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setText(text);
		javaMailSender.send(msg);
	}

	@Override
	public String lastUserImportJob() {
		return repository.lastUserImportJob();
	}
}
