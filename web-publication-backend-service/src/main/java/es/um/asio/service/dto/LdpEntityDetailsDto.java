package es.um.asio.service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LdpEntityDetailsDto {

	private String uri;
	
	private List<LdpEntityDetail> properties;
		
	private List<LdpEntityDetailsDto> relations;
	
	public static LdpEntityDetail buildDetail(String key, String value) {
		return new LdpEntityDetail(key, value);
	}
	
	@AllArgsConstructor
	@Getter
	public static class LdpEntityDetail {
		
		private String key;
		
		private String value;
	}
}
