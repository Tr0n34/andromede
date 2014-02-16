package fr.andromede.common.exceptions;

public class BusinessServiceException extends RuntimeException  {

	private static final long serialVersionUID = -7589933858597368933L;
	
	private String code;
	
	public BusinessServiceException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}

}
