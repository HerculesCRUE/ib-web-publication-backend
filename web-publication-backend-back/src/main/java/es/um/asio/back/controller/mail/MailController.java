package es.um.asio.back.controller.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.asio.back.util.Constants;
import es.um.asio.service.service.mail.EmailService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Book controller.
 */
@RestController
@RequestMapping(MailController.Mappings.BASE)
public class MailController {

	@Autowired
	private EmailService emailService;

	@Value("${spring.mail.username}")
	private String to;

	@PostMapping(MailController.Mappings.SEND)
	public String sendMail(@RequestBody final Object request) throws Exception {
		List<String> mails = new ArrayList<>();
		mails.add(to);
		final HashMap map = (HashMap) request;
		if (map.get("subject") == null || map.get("text") == null) {
			throw new Exception("Attributes  subject and text are required");
		}

		String subject = (String) map.get("subject");
		String text = (String) map.get("text");
		emailService.sendSimpleMail(mails, subject, text);
		return "{\"status\":\"done\"}";
	}

	@PostMapping(MailController.Mappings.SEND_TYPE)
	public void sendMailType(@RequestBody final String type) throws Exception {
		List<String> mails = new ArrayList<>();
		String subject = null;
		String text = null;
		if (Constants.IMPORT.equals(type)) {
			mails.add(emailService.lastUserImportJob());
			subject = Constants.SUBJECT_IMPORT;
			text = Constants.EMAIL_BODY_IMPORT;
		}

		if (mails.size() > 0)
			emailService.sendSimpleMail(mails, subject, text);
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {

		public static final String SEND_TYPE = "/sendType";

		/**
		 * Controller request mapping.
		 */
		protected static final String BASE = "/email";

		/**
		 * Mapping for search.
		 */
		protected static final String SEND = "/send";
	}
}
