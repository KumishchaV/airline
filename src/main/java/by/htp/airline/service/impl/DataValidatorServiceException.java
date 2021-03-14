package by.htp.airline.service.impl;

public class DataValidatorServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataValidatorServiceException() {
		super();
	}

	public DataValidatorServiceException(String message) {
		super(message);
	}

	public DataValidatorServiceException(Exception e) {
		super(e);
	}

	public DataValidatorServiceException(String message, Exception e) {
		super(message, e);
	}
}
