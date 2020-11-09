package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;

public class AssociatedUnableToVoteException extends HttpException {

    private static final String MESSAGE = "error.associatedUnableToVote";
    private static final String MESSAGE_CODE = "error.associatedUnableToVote.code";

    public AssociatedUnableToVoteException() {
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
