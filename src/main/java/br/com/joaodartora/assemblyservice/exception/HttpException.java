package br.com.joaodartora.assemblyservice.exception;

import br.com.joaodartora.assemblyservice.config.exception.Error;
import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {
    public HttpException(String message) {
        super(message);
    }

    public abstract Error getError();

    public abstract HttpStatus getStatus();
}
