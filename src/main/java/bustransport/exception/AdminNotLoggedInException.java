package bustransport.exception;

public class AdminNotLoggedInException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7150751237722127385L;
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

	public AdminNotLoggedInException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public AdminNotLoggedInException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
