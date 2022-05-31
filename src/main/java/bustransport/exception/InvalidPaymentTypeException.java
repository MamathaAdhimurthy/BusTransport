package bustransport.exception;

public class InvalidPaymentTypeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 526908139055269880L;
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

	public InvalidPaymentTypeException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public InvalidPaymentTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
