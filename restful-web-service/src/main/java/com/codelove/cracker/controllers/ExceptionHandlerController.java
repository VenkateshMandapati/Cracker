package com.codelove.cracker.controllers;

import com.codelove.cracker.errors.GenericError;
import com.codelove.cracker.errors.RequestError;
import com.codelove.cracker.exception.CrackerException;
import com.codelove.cracker.exception.InvalidRequestException;

import com.codelove.cracker.exception.NDBAPIException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler
	public ResponseEntity<List<RequestError>> handleInvalidRequestException(InvalidRequestException ex){
	    logger.error(ex.getStackTrace());
        return new ResponseEntity<>(ex.getRequestErrorList(), HttpStatus.BAD_REQUEST);
	}

    @ExceptionHandler({CrackerException.class, NDBAPIException.class})
    public ResponseEntity<GenericError> handleCrackerException(RuntimeException ex){
	    
    	HttpStatus httpStatus;
	    String message;

        if(ex instanceof CrackerException){
            CrackerException crackerException = (CrackerException) ex;
            httpStatus = crackerException.getHttpStatus();
            message = crackerException.getMessage();
        }
        else if(ex instanceof NDBAPIException){
            NDBAPIException ndbAPIException = (NDBAPIException) ex;
            httpStatus = ndbAPIException.getHttpStatus();
            message = ndbAPIException.getMessage();
        }
        else{
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = ex.getMessage();
        }

        ex.printStackTrace();
        GenericError error = new GenericError();
        error.setHttpStatus(httpStatus);
        error.setMessage(message);
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler
    public ResponseEntity<GenericError> handleAllOtherExceptions(Exception ex){
    	ex.printStackTrace();
        GenericError error = new GenericError();
        error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage("Failed because of Unknown reason");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
