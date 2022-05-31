package bustransport.exception;

public class AccountLogoutException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4669694942055552813L;
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

	public AccountLogoutException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public AccountLogoutException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
