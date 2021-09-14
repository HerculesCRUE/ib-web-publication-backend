package es.um.asio.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LdpEntityRelatedDto {
	
	private String relationship;
	
	private String relatedUri;
	
	private String relatedType;
	
	private String relatedDescription;
}
