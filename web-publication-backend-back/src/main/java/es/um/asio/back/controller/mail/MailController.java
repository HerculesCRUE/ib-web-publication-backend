package es.um.asio.back.controller.mail;

import es.um.asio.service.dto.BookDto;
import es.um.asio.service.filter.book.BookFilter;
import es.um.asio.service.proxy.book.BookProxy;
import es.um.asio.service.service.mail.EmailService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			throw new Exception("Attributes mailList, subject and text are required" );
		}
		if (map.get("mailList") instanceof ArrayList) {
			mails = (List<String>) map.get("mailList");
		} else if(map.get("mailList") instanceof String) {
			mails.add((String)map.get("mailList"));
		}
		String subject = (String) map.get("subject");
		String text = (String) map.get("text");
		emailService.sendSimpleMail(mails, subject, text);
		return "Done";
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	static final class Mappings {
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
