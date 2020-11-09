package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;

public class SessionAlreadyClosedException extends HttpException {

    private static final String MESSAGE = "error.sessionAlreadyClosed";
    private static final String MESSAGE_CODE = "error.sessionAlreadyClosed.code";

    public SessionAlreadyClosedException() {
        super(MESSAGE);
    }

    @Override
    public Error getError() {
        return new Error(MESSAGE, MESSAGE_CODE);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
