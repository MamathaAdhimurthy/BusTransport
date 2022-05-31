package bustransport.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException){
		logger.error(emptyInputException.getErrorCode()+"\t"+emptyInputException.getErrorMessage());
		return new ResponseEntity<String>("EmptyInputException : "+emptyInputException.getErrorCode()+"\\"+emptyInputException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	//User Class
	@ExceptionHandler(AccountLogoutException.class)
	public ResponseEntity<String> handleAccountLogoutException(AccountLogoutException accountLogoutException){
		logger.error("AccountLogoutException : "+accountLogoutException.getErrorCode()+"\\"+accountLogoutException.getErrorMessage());
		return new ResponseEntity<String>("AccountLogoutException : "+accountLogoutException.getErrorCode()+"\\"+accountLogoutException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidUserAgeException.class)
	public ResponseEntity<String> handleInvalidUserAgeException(InvalidUserAgeException invalidUserAgeException){
		logger.error("InvalidUserAgeException : "+invalidUserAgeException.getErrorCode()+"\\"+invalidUserAgeException.getErrorMessage());
		return new ResponseEntity<String>("InvalidUserAgeException : "+invalidUserAgeException.getErrorCode()+"\\"+invalidUserAgeException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	//Bus Route Class
	@ExceptionHandler(InvalidRouteStatusException.class)
	public ResponseEntity<String> handleInvalidRouteStatusException(InvalidRouteStatusException invalidRouteStatusException){
		logger.error("InvalidRouteStatusException : "+invalidRouteStatusException.getErrorCode()+"\\"+invalidRouteStatusException.getErrorMessage());
		return new ResponseEntity<String>("InvalidRouteStatusException : "+invalidRouteStatusException.getErrorCode()+"\\"+invalidRouteStatusException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BusRouteAvailabilityException.class)
	public ResponseEntity<String> handleBusRouteAvailabilityException(BusRouteAvailabilityException busRouteAvailabilityException){
		logger.error("BusRouteAvailabilityException : "+busRouteAvailabilityException.getErrorCode()+"\\"+busRouteAvailabilityException.getErrorMessage());
		return new ResponseEntity<String>("BusRouteAvailabilityException : "+busRouteAvailabilityException.getErrorCode()+"\\"+busRouteAvailabilityException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AdminNotLoggedInException.class)
	public ResponseEntity<String> handleAdminNotLoggedInException(AdminNotLoggedInException adminNotLoggedInException){
		logger.error("AdminNotLoggedInException : "+adminNotLoggedInException.getErrorCode()+"\\"+adminNotLoggedInException.getErrorMessage());
		return new ResponseEntity<String>("AdminNotLoggedInException : "+adminNotLoggedInException.getErrorCode()+"\\"+adminNotLoggedInException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	

	//Bus Class
	
	@ExceptionHandler(InvalidBusClassException.class)
	public ResponseEntity<String> handleInvalidBusClassException(InvalidBusClassException invalidBusClassException){
		logger.error("InvalidBusClassException : "+invalidBusClassException.getErrorCode()+"\\"+invalidBusClassException.getErrorMessage());
		return new ResponseEntity<String>("InvalidBusClassException : "+invalidBusClassException.getErrorCode()+"\\"+invalidBusClassException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InsufficientSeatsAvailableException.class)
	public ResponseEntity<String> handleInsufficientSeatsAvailableException(InsufficientSeatsAvailableException insufficientSeatsAvailableException){
		logger.error("InsufficientSeatsAvailableException : "+insufficientSeatsAvailableException.getErrorCode()+"\\"+insufficientSeatsAvailableException.getErrorMessage());
		return new ResponseEntity<String>("InsufficientSeatsAvailableException : "+insufficientSeatsAvailableException.getErrorCode()+"\\"+insufficientSeatsAvailableException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	//Booking Class
	@ExceptionHandler(UserNotLoggedInException.class)
	public ResponseEntity<String> handleUserNotLoggedInException(UserNotLoggedInException userNotLoggedInException){
		logger.error("UserNotLoggedInException : "+userNotLoggedInException.getErrorCode()+"\\"+userNotLoggedInException.getErrorMessage());
		return new ResponseEntity<String>("UserNotLoggedInException : "+userNotLoggedInException.getErrorCode()+"\\"+userNotLoggedInException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidPaymentTypeException.class)
	public ResponseEntity<String> handleInvalidPaymentTypeException(InvalidPaymentTypeException invalidPaymentTypeException){
		logger.error("InvalidPaymentTypeException : "+invalidPaymentTypeException.getErrorCode()+"\\"+invalidPaymentTypeException.getErrorMessage());
		return new ResponseEntity<String>("InvalidPaymentTypeException : "+invalidPaymentTypeException.getErrorCode()+"\\"+invalidPaymentTypeException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<String> handleInvalidUserIdException(InvalidUserIdException invalidUserIdException){
		logger.error("InvalidUserIdException : "+invalidUserIdException.getErrorCode()+"\\"+invalidUserIdException.getErrorMessage());
		return new ResponseEntity<String>("InvalidUserIdException : "+invalidUserIdException.getErrorCode()+"\\"+invalidUserIdException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	//--------------------------
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException){
		logger.error("NoSuchElementException: Entered Data doesn't exist ,So Operation cannot be Performed.");
		return new ResponseEntity<String>("NoSuchElementException: Entered Data doesn't exist ,So Operation cannot be Performed. ",HttpStatus.NOT_FOUND);
	}
}
