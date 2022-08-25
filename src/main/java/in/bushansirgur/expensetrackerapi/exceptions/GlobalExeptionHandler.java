package in.bushansirgur.expensetrackerapi.exceptions;

import in.bushansirgur.expensetrackerapi.entity.dto.exception.ErrorBodyDto;
import in.bushansirgur.expensetrackerapi.entity.dto.exception.ErrorObjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExeptionHandler {//extends ResponseEntityExceptionHandler {

    /**
     * custom Exception for Expenses
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObjectDto> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ErrorObjectDto errorObject = new ErrorObjectDto();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());

        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        errorObject.setErrorBodyDto( new ErrorBodyDto(
                        ex.getStackTrace()[0].getFileName(),
                        ex.getStackTrace()[0].getMethodName(),
                        ex.getStackTrace()[0].getLineNumber(),
                        errors
                )
        );


        return new ResponseEntity<ErrorObjectDto>(errorObject, HttpStatus.NOT_FOUND);
    }

    /**
     * Handling Bad Request Exception
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObjectDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {

        ErrorObjectDto errorObject = new ErrorObjectDto();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setTimestamp(new Date());

        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());

        errorObject.setErrorBodyDto( new ErrorBodyDto(
                        ex.getStackTrace()[0].getFileName(),
                        ex.getStackTrace()[0].getMethodName(),
                        ex.getStackTrace()[0].getLineNumber(),
                        errors
                )
        );

        return new ResponseEntity<ErrorObjectDto>(errorObject, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling the General Exception
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObjectDto> handleGeneralException(Exception ex, WebRequest request) {

        ErrorObjectDto errorObject = new ErrorObjectDto();
        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setTimestamp(new Date());

        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());

        errorObject.setErrorBodyDto( new ErrorBodyDto(
                        ex.getStackTrace()[0].getFileName(),
                        ex.getStackTrace()[0].getMethodName(),
                        ex.getStackTrace()[0].getLineNumber(),
                        errors
                )
        );
        return new ResponseEntity<ErrorObjectDto>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handling valid exception (Validator Annotations Pojo)
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObjectDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        ErrorObjectDto errorObject = new ErrorObjectDto();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setTimestamp(new Date());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        errorObject.setErrorBodyDto( new ErrorBodyDto(
                        ex.getStackTrace()[0].getFileName(),
                        ex.getStackTrace()[0].getMethodName(),
                        ex.getStackTrace()[0].getLineNumber(),
                        errors
                )
        );

        return new ResponseEntity<ErrorObjectDto>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemAreadyExistsExeption.class)
    public ResponseEntity<ErrorObjectDto> handleItemAreadyExistsExeption(ItemAreadyExistsExeption ex, WebRequest request) {
        ErrorObjectDto errorObject = new ErrorObjectDto();
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setTimestamp(new Date());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        errorObject.setErrorBodyDto( new ErrorBodyDto(
                        ex.getStackTrace()[0].getFileName(),
                        ex.getStackTrace()[0].getMethodName(),
                        ex.getStackTrace()[0].getLineNumber(),
                        errors
                )
        );

        return new ResponseEntity<ErrorObjectDto>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorObjectDto> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorObjectDto errorObject = new ErrorObjectDto();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        errorObject.setErrorBodyDto( new ErrorBodyDto(
                        ex.getStackTrace()[0].getFileName(),
                        ex.getStackTrace()[0].getMethodName(),
                        ex.getStackTrace()[0].getLineNumber(),
                        errors
                )
        );

        return new ResponseEntity<ErrorObjectDto>(errorObject, HttpStatus.NOT_FOUND);
    }



}
