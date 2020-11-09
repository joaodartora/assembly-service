package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;

public class SessionAlreadyOpenException extends HttpException {

    private static final String MESSAGE = "error.sessionAlreadyOpen";
    private static final String MESSAGE_CODE = "error.sessionAlreadyOpen.code";

    public SessionAlreadyOpenException() {
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
