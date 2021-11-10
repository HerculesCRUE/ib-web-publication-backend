package es.um.asio.back.controller.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping(MailController.Mappings.SEND)
	public String sendMail(@RequestBody final Object request) throws Exception {
		List<String> mails = new ArrayList<>();
		final HashMap map = (HashMap) request;
		if (map.get("mailList") == null || map.get("subject") == null || map.get("text") == null) {
			throw new Exception("Attributes mailList, subject and text are required");
		}
		if (map.get("mailList") instanceof ArrayList) {
			mails = (List<String>) map.get("mailList");
		} else if (map.get("mailList") instanceof String) {
			mails.add((String) map.get("mailList"));
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
