package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.client.UserInfoClient;
import br.com.joaodartora.assemblyservice.client.response.CpfValidationResponse;
import br.com.joaodartora.assemblyservice.exception.AssociatedUnableToVoteException;
import br.com.joaodartora.assemblyservice.type.VotePossibilityEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AssociatedServiceTest {

    private final UserInfoClient userInfoClient;
    private final AssociatedService associatedService;

    public AssociatedServiceTest() {
        this.userInfoClient = mock(UserInfoClient.class);
        this.associatedService = new AssociatedService(userInfoClient);
    }

    @Test
    void verifyAssociatedPermissionToVoteWithSuccessShouldDoNothing() {
        when(userInfoClient.getCpfValidationToVote("24440570019")).thenReturn(new CpfValidationResponse(VotePossibilityEnum.ABLE_TO_VOTE));

        associatedService.verifyAssociatedPermissionToVote("24440570019");
    }

    @Test
    void verifyAssociatedPermissionToVoteWithAssociatedUnableToVoteShouldThrowAssociatedUnableToVoteException() {
        when(userInfoClient.getCpfValidationToVote("24440570019")).thenReturn(new CpfValidationResponse(VotePossibilityEnum.UNABLE_TO_VOTE));

        AssociatedUnableToVoteException exception = assertThrows(AssociatedUnableToVoteException.class, () -> associatedService.verifyAssociatedPermissionToVote("24440570019"));

        assertEquals("error.associatedUnableToVote", exception.getMessage());
    }

}
