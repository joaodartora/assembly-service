package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;

public class SessionNotFoundException extends HttpException {

    private static final String MESSAGE = "error.sessionNotFound";
    private static final String MESSAGE_CODE = "error.sessionNotFound.code";

    public SessionNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public Error getError() {
        return new Error(MESSAGE, MESSAGE_CODE);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
