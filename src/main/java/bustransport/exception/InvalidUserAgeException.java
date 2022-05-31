package bustransport.exception;

public class InvalidUserAgeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4834240994763549366L;
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

	public InvalidUserAgeException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public InvalidUserAgeException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
