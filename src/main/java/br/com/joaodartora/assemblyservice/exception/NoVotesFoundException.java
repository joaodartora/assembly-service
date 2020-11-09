package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;

public class NoVotesFoundException extends HttpException {

    private static final String MESSAGE = "error.noVotesFound";
    private static final String MESSAGE_CODE = "error.noVotesFound.code";

    public NoVotesFoundException() {
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
