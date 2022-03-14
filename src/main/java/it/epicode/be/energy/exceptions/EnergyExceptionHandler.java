package it.epicode.be.energy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EnergyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EnergySystemException.class)
	protected ResponseEntity<Object> handleEpicodeException(EnergySystemException ex) {

		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}