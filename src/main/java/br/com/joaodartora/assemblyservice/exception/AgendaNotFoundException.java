package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;

public class AgendaNotFoundException extends HttpException {

    private static final String MESSAGE = "error.agendaNotFound";
    private static final String MESSAGE_CODE = "error.agendaNotFound.code";

    public AgendaNotFoundException() {
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
