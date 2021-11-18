package es.um.asio.service.service.mail.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import es.um.asio.service.repository.SparqlQueryRepository;
import es.um.asio.service.service.mail.EmailService;

@Service
public class EmailServiceImp implements EmailService {
	
	private final Logger logger = LoggerFactory.getLogger(EmailServiceImp.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired(required = true)
	private SparqlQueryRepository repository;
	
	 @Value("${spring.mail.username}")
	private String from;

	@Override
	public void sendSimpleMail(List<String> tos, String subject, String text) {
		logger.info("JavaMailProperties {} ",((JavaMailSenderImpl)javaMailSender).getJavaMailProperties()!=null ? ((JavaMailSenderImpl)javaMailSender).getJavaMailProperties().toString():"");
		logger.info("Sending mail from {} subject {} ",from ,subject);
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
