package com.example.blog.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
	
	
    // handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
		
        ApiResponse apiResponse = new ApiResponse(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		
	}	
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException webRequest){
//		Map<String,String> resp=new HashMap<>();
//		webRequest.getBindingResult().getAllErrors().forEach((error)->{
//			String field = ((FieldError) error).getField();
//			String defaultMessage = error.getDefaultMessage();
//			resp.put(field, defaultMessage);
//		});
//		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
//		
//	}
	
	
//	// handle global exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse> handleAllException(
//            Exception exception,
//            WebRequest webRequest){
//
//        ApiResponse errorDetails = new ApiResponse(new Date(), exception.getMessage(),
//                webRequest.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
}

	
	
	


