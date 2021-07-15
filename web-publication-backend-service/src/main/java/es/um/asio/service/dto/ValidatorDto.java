package es.um.asio.service.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3969987716938086572L;
	
	private String id;

	private String entity;
	
	private String validator;	
}
