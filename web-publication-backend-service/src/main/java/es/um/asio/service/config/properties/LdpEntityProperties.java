package es.um.asio.service.config.properties;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LdpEntityProperties {

	private String type;
	private List<String> back;
	private List<String> forward;
}
