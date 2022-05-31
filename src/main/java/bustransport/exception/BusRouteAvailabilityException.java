package bustransport.exception;

public class BusRouteAvailabilityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3338869994381766093L;
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

	public BusRouteAvailabilityException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BusRouteAvailabilityException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
