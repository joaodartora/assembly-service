package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ClientException extends HttpException {

    private static final String MESSAGE = "error.genericError";
    private static final String MESSAGE_CODE = "error.genericError.code";

    public ClientException(HttpClientErrorException clientError) {
        super(clientError.getMessage());
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
