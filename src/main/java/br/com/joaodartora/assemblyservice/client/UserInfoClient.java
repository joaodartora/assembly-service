package br.com.joaodartora.assemblyservice.client;

import br.com.joaodartora.assemblyservice.client.response.CpfValidationResponse;
import br.com.joaodartora.assemblyservice.exception.ClientException;
import br.com.joaodartora.assemblyservice.exception.InvalidCpfException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserInfoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoClient.class);
    private final RestTemplate restTemplate;
    private final String userInfoUrl;

    public UserInfoClient(RestTemplate restTemplate, @Value("${url.user-info}") String userInfoUrl) {
        this.restTemplate = restTemplate;
        this.userInfoUrl = userInfoUrl;
    }

    public CpfValidationResponse getCpfValidationToVote(String cpf) {
        String buildedUrl = buildGetCpfValidationUrl(userInfoUrl, cpf);
        LOGGER.info("Iniciando {} para URL [{}] para validar o CPF {}", HttpMethod.GET, buildedUrl, cpf);
        try {
            CpfValidationResponse response = restTemplate.getForObject(buildedUrl, CpfValidationResponse.class);
            LOGGER.info("Finalizando com sucesso {} para URL [{}] para validar o CPF {}", HttpMethod.GET, buildedUrl, cpf);
            return response;
        } catch (HttpClientErrorException exception) {
            LOGGER.info("Erro com status {} e mensagem {} no request para URL [{}] de validação do CPF {}", exception.getStatusCode(), exception.getMessage(), buildedUrl, cpf);
            if (HttpStatus.NOT_FOUND == exception.getStatusCode())
                throw new InvalidCpfException();
            throw new ClientException(exception);
        }
    }

    private String buildGetCpfValidationUrl(String baseUrl, String cpf) {
        return UriComponentsBuilder.fromUriString(baseUrl)
                .pathSegment("users", cpf)
                .toUriString();
    }
}
