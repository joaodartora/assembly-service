package br.com.joaodartora.assemblyservice.config.exception;

import br.com.joaodartora.assemblyservice.exception.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ErrorTranslator errorTranslator;

    public GlobalExceptionHandler(ErrorTranslator errorTranslator) {
        this.errorTranslator = errorTranslator;
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            final Error errorTranslated = errorTranslator.translate(exception.getError());
            return ResponseEntity
                    .status(exception.getStatus())
                    .body(errorTranslated);
        }
        return getInternalErrorResponse(throwable);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(MethodArgumentNotValidException exception) {
        return Optional.ofNullable(exception)
                .map(MethodArgumentNotValidException::getBindingResult)
                .map(Errors::getFieldError)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(message -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(message, "ABS-000")))
                .orElse(getInternalErrorResponse(exception));
    }

    private ResponseEntity<Error> getInternalErrorResponse(Throwable throwable) {
        LOGGER.error(throwable.getMessage(), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}