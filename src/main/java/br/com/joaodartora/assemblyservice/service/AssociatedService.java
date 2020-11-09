package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.client.UserInfoClient;
import br.com.joaodartora.assemblyservice.client.response.CpfValidationResponse;
import br.com.joaodartora.assemblyservice.exception.AssociatedUnableToVoteException;
import br.com.joaodartora.assemblyservice.type.VotePossibilityEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AssociatedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociatedService.class);
    private final UserInfoClient userInfoClient;

    public AssociatedService(UserInfoClient userInfoClient) {
        this.userInfoClient = userInfoClient;
    }

    public void verifyAssociatedPermissionToVote(String cpf) {
        CpfValidationResponse cpfValidationToVote = userInfoClient.getCpfValidationToVote(cpf);
        if (VotePossibilityEnum.UNABLE_TO_VOTE == cpfValidationToVote.getStatus()) {
            LOGGER.error("The associated with CPF {} is unable to vote!", cpf);
            throw new AssociatedUnableToVoteException();
        }
    }
}
