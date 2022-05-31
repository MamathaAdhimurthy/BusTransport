package bustransport.exception;

public class InvalidUserIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8100721561293219339L;
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

	public InvalidUserIdException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public InvalidUserIdException() {
		super();
		// TODO Auto-generated constructor stub
	}

}
