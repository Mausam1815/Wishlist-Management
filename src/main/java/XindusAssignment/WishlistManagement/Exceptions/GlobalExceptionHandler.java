package XindusAssignment.WishlistManagement.Exceptions;

import XindusAssignment.WishlistManagement.DTOs.ResponseDTOs.GeneralResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler is a centralized exception handler for the application.
 * It handles CustomException and maps it to an appropriate HTTP response.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles CustomException and returns an appropriate HTTP response with a custom error message.
     * @param e The CustomException to handle.
     * @return ResponseEntity containing a GeneralResponseDto with the error message and HTTP status code.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GeneralResponseDto> ExceptionHandler(Exception e){
        GeneralResponseDto response = new GeneralResponseDto();
        response.setMessage(e.getMessage());
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
