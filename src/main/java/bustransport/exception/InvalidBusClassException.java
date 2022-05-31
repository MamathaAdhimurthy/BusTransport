package bustransport.exception;

public class InvalidBusClassException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5016089938103800928L;
	private String errorCode;
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public InvalidBusClassException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public InvalidBusClassException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
