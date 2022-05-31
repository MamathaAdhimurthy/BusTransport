package bustransport.exception;

public class InsufficientSeatsAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1494783853283072247L;
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

	public InsufficientSeatsAvailableException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public InsufficientSeatsAvailableException() {
		super();
		// TODO Auto-generated constructor stub
	}

}
